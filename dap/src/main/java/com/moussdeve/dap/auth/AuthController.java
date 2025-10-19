//*****************************************************************************************************************************************************************************
// * 
// * 
// * 
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.auth;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    
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
        
        return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername()));
    }

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
     *  Usage: http://{IP_ADDRESS/DNS}:{PORT_NUMBER}/dap/api/v1.0/auth/status e.g. http://localhost:8080/dap/api/v1.0/des/status
     ********************************************************************************************************************************************************/
    @GetMapping("status")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("DeepSeek Chat API Service is running");
    }
    
}
