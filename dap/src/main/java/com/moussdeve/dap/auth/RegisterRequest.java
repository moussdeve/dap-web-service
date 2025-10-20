//*************************************************************************************************************************
// *	Title		:	RegisterRequest
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:	RegisterRequest class, holds all registration members
// *=======================================================================================================================
// *
// *	Dependencies:   
// *	Usage		:	
// *	Notes		:	
//*************************************************************************************************************************

package com.moussdeve.dap.auth;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    public RegisterRequest(String username, String password, String email) {
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
