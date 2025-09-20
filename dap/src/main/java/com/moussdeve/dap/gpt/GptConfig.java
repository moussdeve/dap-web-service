//*****************************************************************************************************************************************************************************
// * 
// * 
// * 
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.gpt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GptConfig {
    @Bean
    public WebClient deepSeekWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
            .baseUrl("apiUrl")
            .defaultHeader("Authorization", "Bearer " + "apiKey")
            .defaultHeader("Content-Type", "application/json")
            .build();
    }
}
