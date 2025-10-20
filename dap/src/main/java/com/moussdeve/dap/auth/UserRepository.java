//**********************************************************************************************************************
// *	Title		:	UserRepository
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0

// * 	Description	:	A Spring Data JPA repository interface that rprovides built-in CRUD operations for 
// *                        the User entity without requiring boilerplate code.
// *                    By extending JpaRepository<User, Long>, it automatically inherits methods such as:
// *                        - findAll()
// *                        - findById(Long id)
// *                        - save(User user)
// *                        - deleteById(Long id)
// *                    Additionaly, custom query methods can be defined (such as findByUsername(String userbane)
// *                        as shown below) and Spring will implement them automatically based on naming conventions.
// *===================================================================================================================
// *
// *	Dependencies:   
// *	Usage		:	
// *	Notes		:	
//*********************************************************************************************************************

package com.moussdeve.dap.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}