//*****************************************************************************************************************************************************************************
// *	Title		:	DeepSeekResponseModelJson
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// * 	Description	:	
// *==========================================================================================================================================================================
// *
// *	Dependencies:	
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.deepseek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeepSeekResponseModelJson {
    // private String deepSeekResponseJsonFile;                    // coming soon
    private String deepSeekResponseModelJson;
    private DeepSeekResponseModel deepSeekResponseModel;

    public DeepSeekResponseModelJson(DeepSeekResponseModel responseModel) {
        this.deepSeekResponseModel = responseModel;
    }

    public DeepSeekResponseModelJson(String responseModelJson, boolean isFileName) {
        if (isFileName) {
            // this.deepSeekResponseJsonFile = responseModelJson;
        }
        else {
            this.deepSeekResponseModelJson = responseModelJson;
        }
    }

    // For unit testing purposes
    private void JsonToModel() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (this.deepSeekResponseModel == null) {
                this.deepSeekResponseModel = mapper.readValue(this.deepSeekResponseModelJson, DeepSeekResponseModel.class);
                System.out.println("#### " + this.deepSeekResponseModel.getModel());
            }
            
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public DeepSeekResponseModel getResponseModel() {
        if (this.deepSeekResponseModel == null) {
            JsonToModel();
        }
        return this.deepSeekResponseModel;
    }

}
