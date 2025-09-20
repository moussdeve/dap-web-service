//*****************************************************************************************************************************************************************************
// *	Title		:	ApiConfigService
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

package com.moussdeve.dap.globalprocess;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiConfigService {

    @Autowired
    private ApiConfigRepository repository;
    
    public List<ApiConfig> findAll() {
        return repository.findAll();
    }

    public Optional<ApiConfig> findById(Long id) {
        Optional<ApiConfig> config = repository.findById(id);
        return config;
    }

    public Optional<ApiConfig> findByName(String name) {
        return findAll().stream().filter(cfg -> (cfg.getLlmName().equalsIgnoreCase(name))).findFirst();
    }

    public ApiConfig save(ApiConfig config) {
        ApiConfig savedConfig = repository.save(config);
        return savedConfig;
    }
    
    public ApiConfig update(Long id, ApiConfig updatedConfig) {
        return repository.findById(id).map(config ->
        {
            config.setUri(updatedConfig.getUri());
            config.setToken(updatedConfig.getToken());
            config.setLlmName(updatedConfig.getLlmName());
            return repository.save(config);
        })
        .orElse(new ApiConfig());
    }

    // Delete a configuration
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    // Check if configuration exits 
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
