//********************************************************************************************************************
// *	Title		:	CustomUserDetailsService
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:	Custom implementation of Sppring Security's UserDetailService interface
// *                        Provides a method to load user information from custom data source (MySQL)
// *                        This service is automatically called by Spring Security when a user attempts to log in
// *                        It must return a UserDetails object representing the authenticated user.
// *==================================================================================================================
// *
// *	Dependencies:   
// *	Usage		:	
// *	Notes		:	
//********************************************************************************************************************

package com.moussdeve.dap.auth;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    // Custom implementation of loadUserByUsername that loads user data (username, password, state) from the database.
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.moussdeve.dap.auth.User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .disabled(!user.isEnabled())
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .authorities(Collections.emptyList())
            .build();
    }
}
