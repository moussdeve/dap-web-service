//****************************************************************************************************************************
// *	Title		:	ApiPromptRepository
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:	Implements Java Persistent API (JPA) for Prompt object
// *==========================================================================================================================
// *
// *	Dependencies:
// *	Usage		:	
// *	Notes		:	
//****************************************************************************************************************************

package com.moussdeve.dap.prompt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiPromptRepository extends JpaRepository<Prompt, Long> {
    // add custom methods here
}
