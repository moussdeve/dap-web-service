//*************************************************************************************************************
// *	Title		:	
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// * 	Description	:	
// *===========================================================================================================
// *
// *	Dependencies:   
// *	Usage		:	
// *	Notes		:	
//*************************************************************************************************************

package com.moussdeve.dap.auth;

public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
