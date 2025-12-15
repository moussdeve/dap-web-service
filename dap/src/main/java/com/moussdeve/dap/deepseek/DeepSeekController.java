//*****************************************************************************************************************************************************************************
// *	Title		:	DeepSeekController
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:	This class implements the RestController exposing DeepSeek data handling HTTP requests and responses in this RESTful web service (API).
// *                        - Receives HTTP requests (GET, POST, PUT, and DELETE)
// *                        - Process the requests (calls the DeepSeek service and computes besiness logic)
// *                        - Returns a response  in JSON format.
// *
// *                    Features:
// *                        Routing - Maps HTTP methods and URLs to specific function
// *                        CRUD Operations - Supports the Create, Read, Update, and Delete operations
// *                        Statelessness - Each request is independent; does not store any session state
// *                        DeepSeek-Resource-Oriented - Operates on DeepSeek resources
// *==========================================================================================================================================================================
// *
// *	Dependencies:	
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.deepseek;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moussdeve.dap.globalprocess.ContentLineEntity;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dap/api/v1.0/des/")
public class DeepSeekController {

    private DeepSeekRequestService deepSeekRequestService;
    
    public DeepSeekController(DeepSeekRequestService deepSeekService) {
        this.deepSeekRequestService = deepSeekService;
    }
    

    /************************************************************************************************************************
     * GET - Operation:
     *  Takes a store name as request parameter and return a JSON list of coupons and promotional codes
     *  Usage:  http://{IP_ADDRESS/DNS}:{PORT_NUMBER}/dap/api/v1.0/des/promoco?store={STORE_NAME}
     *          http://localhost:8080/dap/api/v1.0/des/promoco?store=Walmart
     *  
     * @return List<ContentLineEntity> codes - a list of codes 
     ************************************************************************************************************************/
    @GetMapping("promoco")
    @PreAuthorize("isAuthenticated()")
    public List<ContentLineEntity> getCoupons(@RequestParam String store) {
        
        Mono<DeepSeekResponseModel> responseMono = deepSeekRequestService.chatCompletion(store);
        DeepSeekResponseModel response = responseMono.block();
        
        DeepSeekResponseParser parser = new DeepSeekResponseParser(response);
        List<ContentLineEntity> codes = parser.getContent();

        return codes;
    }


    /*************************************************************************************************************************
     * POST - Operation:
     *  Takes name as request parameter and returns a DeepSeekResponseModel JSON of coupons and promotional codes
     * 
     *  Usage:  http://{IP_ADDRESS/DNS}:{PORT_NUMBER}/dap/api/v1.0/des/?store={STORE_NAME}
     *          http://localhost:8080/dap/api/v1.0/des/?store=Walmart
     *  
     * @return Mono<ResponseEntity<DeepSeekResponseModel>> 
     *************************************************************************************************************************/
    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public Mono<ResponseEntity<DeepSeekResponseModel>> searchDeepSeek(@RequestParam String store) {
        
        return deepSeekRequestService.chatCompletion(store)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().build()));
    }


    /**************************************************************************************************************************
     * POST - Operation:
     *  Takes a store DeepSeekRequestModel JSON as request body and returns a DeepSeekResponseModel JSON 
     *      of coupons and promotional codes
     * 
     *  Usage:  http://{IP_ADDRESS/DNS}:{PORT_NUMBER}/dap/api/v1.0/des/custom?store={STORE_NAME}
     *          http://localhost:8080/dap/api/v1.0/des/?store=Walmart
     *  
     * @return Mono<ResponseEntity<DeepSeekResponseModel>> 
     ****************************************************************************************************************************/
    @PostMapping("custom")
    @PreAuthorize("isAuthenticated()")
    public Mono<ResponseEntity<DeepSeekResponseModel>> getCustomCompletion(@RequestBody DeepSeekRequestModel store) {
        
        String storeName;

        if (store == null || this.deepSeekRequestService == null) {
            return Mono.just(ResponseEntity.badRequest().build());
        }

        if (!store.getMessages().isEmpty()) {
            storeName = store.getMessages().get(0).getContent();

            return deepSeekRequestService.chatCompletion(storeName)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().build()));
        }

        return Mono.just(ResponseEntity.badRequest().build());
        
    }


    /******************************************************************************************************************************
     * healthCheck:
     *  Return an Ok 200 response when the api is running. This is a status check method
     * 
     *  Usage: http://{IP_ADDRESS/DNS}:{PORT_NUMBER}/dap/api/v1.0/des/status
     *         http://localhost:8080/dap/api/v1.0/des/status
     * 
     * @return ResponseEntity - "DeepSeek Chat API Service is running"
     *****************************************************************************************************************************/
    @GetMapping("status")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("DAP - DeepSeek Chat API Service is running");
    }
}
