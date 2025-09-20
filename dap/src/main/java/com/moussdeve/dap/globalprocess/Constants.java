//*****************************************************************************************************************************************************************************
// *	Title		:	Constants
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Friday 17th September, 2025
// *	Version		:	v1.0.0
// * 	Description	:	Store fixed values used and reused accross multiple parts of the web service (API). Improves maintainablity and readability, and consistency
// *                        by grouping them here.
// *==========================================================================================================================================================================
// *
// *	Dependencies:	NONE
// *	Usage		:	What's allowed here:
// *                        - API endpoints
// *                        - Configuration keys
// *                        - Error messages
// *                        - Status Codes
// *                        - Mathematical constants
// *                        - File paths
// *                        - Role names and permissions
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.globalprocess;

public class Constants {
    private Constants() {}      // Prevents instatiation

    // Prompt titles
    public static final String DEEPSEEK_TITLE = "DeepSeek";
    public static final String CHATGPT__TITLE = "ChatGpt_"; 
    public static final String GEMINI___TITLE = "G-Gemini";
    public static final String COPILOT__TITLE = "Copilot_";   
}
