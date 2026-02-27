//*****************************************************************************************************************************************************************************
// *	Title		:	AuthController
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// * 	Description	:	Exposes authentication CRUD operations to HTTP requests. Performs user registration and login
// *==========================================================================================================================================================================
// *
// *	Dependencies:   Spring Security | JWT | 
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/dap/api/v1.0/auth/")
public class AuthController {

    // Core Spring Security interface
    // Authenticates user credentials and returns an authenticated object
    @Autowired
    private AuthenticationManager authenticationManager;


    // Custom implementation of UserDetailsService that loads 
    //  user data (username, password, ...) from the database.
    @Autowired
    private CustomUserDetailsService userDetailsService;


    // Utility object for generating, parsing, and 
    // validating JWT tokens in stateless authentication
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    // User service-layer object manages the user entity
    // Handles user-related business logic such as registration,
    // updates, and retrieval between controller and repository layers.
    @Autowired
    private UserService userService;
    

    // Exposes and handles login HTTP request
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException bce) {
            return ResponseEntity.badRequest().body("Invalid username and or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Setting Cookie in ResponseEntity
        ResponseCookie cookie = ResponseCookie.from("DAP-JWT", token)
            .httpOnly(true)
            .secure(false)              // Set to true in production with HTTPS
            .path("/")
            .maxAge(24 * 60 * 60)               // 1 day
            //.domain("localhost")
            .build();

        return ResponseEntity.ok().header("Set-Cookie", cookie.toString())
            .body(new AuthResponse(token, userDetails.getUsername()));
        
        // return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername()));
    }

    
    // Exposes and handles registration HTTP request
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {

        try {
            // Check if username already exists
            if (userService.existsByUsername(registerRequest.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }

            // Check if email already exists
            if (userService.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            // Register and return user
            User user = userService.createUser(registerRequest);
            return ResponseEntity.ok("User " + user.getUsername() + " registered successfully!!");
        } 
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
        
    }

    /*********************************************************************************************************************************************************
     * healthCheck:
     *  Return an Ok 200 response when the api is running. This is a status check method
     * 
     *  Usage: http://{IP_ADDRESS/DNS}:{PORT_NUMBER}/dap/api/v1.0/auth/status 
     *         http://localhost:8080/dap/api/v1.0/des/status
     * 
     * @return ResponseEntity - "Authentication API Service is running"
     ********************************************************************************************************************************************************/
    @GetMapping("status")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("DAP - DES Authentication API is running...");
    }
    
}
