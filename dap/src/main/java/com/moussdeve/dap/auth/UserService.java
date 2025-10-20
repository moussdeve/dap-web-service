//***********************************************************************************************************
// *	Title		:	UserService
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:	A custom service-layer class that manages all business logic related to users.
// *                    Behaves as an intermediary between the controller (handling HTTP request) and the 
// *                        repository (database operations).
// *                    It is reponsible for creating and updating user accounts, fetching user information, 
// *                        managing roles and permissions, handling password changes or encoding.
// *                    It integrates with authentication components like CustomUserDetailsService
// *=========================================================================================================
// *
// *	Dependencies:   
// *	Usage		:	
// *	Notes		:	
//***********************************************************************************************************

package com.moussdeve.dap.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User createUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setEnabled(true);

        return userRepository.save(user);
    }
}
