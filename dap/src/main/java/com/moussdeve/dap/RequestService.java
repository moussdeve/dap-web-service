//*****************************************************************************************************************************************************************************
// *	Title		:	RequestService
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// * 	Description	:	
// *==========================================================================================================================================================================
// *
// *	Dependencies:	NONE
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap;

import com.moussdeve.dap.deepseek.DeepSeekRequestModel;
import com.moussdeve.dap.deepseek.DeepSeekResponseModel;

import reactor.core.publisher.Mono;

public interface RequestService {
    Mono<DeepSeekResponseModel> chatCompletion(String message);
    Mono<DeepSeekResponseModel> chatCompletion(DeepSeekRequestModel request);
    DeepSeekRequestModel createRequest(String message);
}