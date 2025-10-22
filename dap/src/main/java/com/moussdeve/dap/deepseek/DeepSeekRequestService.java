//***************************************************************************************************************
// *	Title		:	DeepSeekRequestService
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// * 	Description	:	Custom service layer component, handles business logic, and data operations for 
// *                        Processing API requests between controller and repository layer.
// *                        Performs client requests to DeepSeek
// *=============================================================================================================
// *
// *	Dependencies:
// *	Usage		:	
// *	Notes		:	
//***************************************************************************************************************

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

    private ApiConfig getApiConfig() {
        return apiConfigService.findAll().get(0);
    }

    @Override
    public Mono<DeepSeekResponseModel> chatCompletion(String message) {
        Prompt prompt = apiPromptService.findByTitle(Constants.DEEPSEEK_TITLE).get();
        DeepSeekRequestModel request = createRequest(prompt.getPromptText() + ": " + message);

        ApiConfig apiConfig = getApiConfig();
        String uri = apiConfig.getUri();
        String token = apiConfig.getToken();

        return webClient.post()
            .uri(uri)
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(DeepSeekResponseModel.class);
    }

    /*
     * Method   : chatCompletion
     * Input    : DeepSeekRequestModel
     * Return   : Mono<DeepSeekResponseModel>
     * Desc     : Takes the input and performs an API request and returns a response
     */
    @Override
    public Mono<DeepSeekResponseModel> chatCompletion(DeepSeekRequestModel request) {

        ApiConfig apiConfig = getApiConfig();
        String uri = apiConfig.getUri();
        String token = apiConfig.getToken();

        // Start an HTTP POST request and returns the results of that call
        return webClient.post()         // creates a POST request builder
            .uri(uri)                   // sets the target api
            .header("Content-Type", "application/json")     // adds HTTP headers
            .header("Authorization", "Bearer " + token)                     // adds authorization-bearer token to the header
            .bodyValue(request)         // attaches a JSON or object payload to send
            .retrieve()                 // executes the request and prepares to handle the response
            .bodyToMono(DeepSeekResponseModel.class);       //  convert original response to DeepSeekResponseModel
    }

    /*
     * Method   : createRequest
     * Input    : message
     * Return   : DeepSeekRequestModel
     * Desc     :   Construct and returns a request model in the appropriate format
     */
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
