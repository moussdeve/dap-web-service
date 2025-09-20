//*****************************************************************************************************************************************************************************
// *	Title		:	DeepSeekRequestService
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// * 	Description	:	Performs client requests to DeepSeek
// *==========================================================================================================================================================================
// *
// *	Dependencies:	NONE
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.deepseek;

import java.util.List;

import com.moussdeve.dap.RequestService;
import com.moussdeve.dap.globalprocess.ApiConfig;
import com.moussdeve.dap.globalprocess.ApiConfigService;
import com.moussdeve.dap.prompt.ApiPromptService;
import com.moussdeve.dap.prompt.Prompt;
import com.moussdeve.dap.globalprocess.Constants;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DeepSeekRequestService implements RequestService {

    private final WebClient webClient;

    @Autowired
    private ApiConfigService apiConfigService;
    @Autowired
    private ApiPromptService apiPromptService;
    
    public DeepSeekRequestService(WebClient deepSeekWebClient) {
        this.webClient = deepSeekWebClient;
    }

    @Override
    public Mono<DeepSeekResponseModel> chatCompletion(String message) {
        Prompt prompt = apiPromptService.findByTitle(Constants.DEEPSEEK_TITLE).get();
        DeepSeekRequestModel request = createRequest(prompt.getPromptText() + ": " + message);
        ApiConfig config = apiConfigService.findAll().get(0);

        return webClient.post()
            .uri(/*"https://api.deepseek.com/chat/completions"*/ config.getUri())
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + config.getToken())
            .bodyValue(request)
            .retrieve()
            .bodyToMono(DeepSeekResponseModel.class);
    }

    @Override
    public Mono<DeepSeekResponseModel> chatCompletion(DeepSeekRequestModel request) {
        return webClient.post()
            .uri("https://api.deepseek.com/chat/completions")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + "sk-9bdd964ff0b24b5a9c688e62962835a9")
            .bodyValue(request)
            .retrieve()
            .bodyToMono(DeepSeekResponseModel.class);
    }

    @Override
    public DeepSeekRequestModel createRequest(String message) {
        DeepSeekRequestModel.Message userMessage = new DeepSeekRequestModel.Message("user", message);

        return new DeepSeekRequestModel(
            "deepseek-chat",
            List.of(userMessage),
            0.7,
            1000,
            false
        );
    }
}
