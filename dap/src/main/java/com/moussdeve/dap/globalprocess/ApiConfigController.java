//*****************************************************************************************************************************************************************************
// *	Title		:	ApiConfigController
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:	This class implements the RestController exposing Configuration data, handling HTTP requests and responses in this RESTful web service (API).
// *                        Serves as an interface between users and overall Config service
// *                        - Receives HTTP requests (GET, POST, PUT, and DELETE)
// *                        - Process the requests (calls the Config service and computes besiness logic)
// *                        - Returns a response  in JSON format.
// *
// *                    Features:
// *                        Routing - Maps HTTP methods and URLs to specific function
// *                        CRUD Operations - Supports the Create, Read, Update, and Delete operations
// *                        Statelessness - Each request is independent; does not store any session state
// *                        Config-Resource-Oriented - Operates on Config resources
// *==========================================================================================================================================================================
// *
// *	Dependencies:	NONE
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.globalprocess;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/dap/api/config")
public class ApiConfigController {

    // private ApiConfigRepository repository;
    @Autowired
    private ApiConfigService apiConfigService;

    // Get all configurations
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<ApiConfig> getAllConfig() {
        return apiConfigService.findAll();
    }

    // Get configuration by ID
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiConfig> getConfigById(@PathVariable Long id) {
        Optional<ApiConfig> config = apiConfigService.findById(id);
        return config.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create a new configuration
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiConfig> createConfig(@Valid @RequestBody ApiConfig config) {
        ApiConfig savedConfig = apiConfigService.save(config);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConfig);
    }
    
    // Update an existing configuration
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiConfig> updateConfig(@PathVariable Long id, @RequestBody ApiConfig updatedConfig) {
        return apiConfigService.findById(id).map(config ->
        {
            config.setUri(updatedConfig.getUri());
            config.setToken(updatedConfig.getToken());
            config.setLlmName(updatedConfig.getLlmName());
            return ResponseEntity.ok(apiConfigService.save(config));
        })
        .orElse(ResponseEntity.notFound().build());
    }

    // Delete a configuration
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteConfig(@PathVariable Long id) {
        if (apiConfigService.existsById(id)) {
            apiConfigService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    /*********************************************************************************************************************************************************
     * heallthCheck:
     *  Return an Ok 200 response when the api is running. This is a status check method
     *  Usage: http://{IP_ADDRESS/DNS}:{PORT_NUMBER}/dap/api/v1.0/config/status
     *         http://localhost:8080/dap/api/v1.0/config/status
     ********************************************************************************************************************************************************/
    @GetMapping("status")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Configuration API Service is running");
    }
    
}
