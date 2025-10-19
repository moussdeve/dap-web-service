//*****************************************************************************************************************************************************************************
// *	Title		:	DeepSeekResponseParser
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// * 	Description	:	Response parser
// *==========================================================================================================================================================================
// *
// *	Dependencies:	
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.deepseek;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.moussdeve.dap.globalprocess.ContentEntity;
import com.moussdeve.dap.globalprocess.ContentLineEntity;

public class DeepSeekResponseParser implements ContentEntity{
    private DeepSeekResponseModel responseModel;
    private List<ContentLineEntity> lines;
    private final String startRegex = "\\*\\*(\\w+)\\*\\*\\s*\\|\\s*(.*?)\\s*\\|\\s*(.*?)\\s*\\|\\s*(.*?)\\s*\\|";

    // Constructor taking DeepSeekResponseModel 
    public DeepSeekResponseParser(DeepSeekResponseModel model) {
        lines = new ArrayList<>();
        this.responseModel = model;
    }

    /*********************************************************************************************************************************************************
     * processContent:
     *  Process/parses the content of the response model and assignes each coupon/promtional code to a list
     ********************************************************************************************************************************************************/
    @Override
    public void processContent() {
        // Skip models containing empty responses
        if (this.responseModel == null || this.responseModel.getChoices().isEmpty()) {
            return;
        }

        List<String> promoParts = new ArrayList<>();                                                      // Stores each promo codes part in the list
        final String content = this.responseModel.getChoices().get(0).getMessage().getContent();    // Get the model response content to parse
        Pattern pattern = Pattern.compile(startRegex);                                                    // Regular expression to match content's table row
        Matcher matcher = pattern.matcher(content);                                                       // Find matching rows

        int tracker = 1;
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                promoParts.add(matcher.group(i));
                if (tracker % matcher.groupCount() == 0) {

                    ContentLineEntity promoEntity = new ContentLineEntity(promoParts);
                    lines.add(promoEntity);     // add each row to list

                    tracker = 1;                // reset row tracker
                    promoParts.clear();         // reset promo parts after each promo code line

                } else {
                    tracker++;
                }
            }
        }
    }

    /*********************************************************************************************************************************************************
     * getContent:
     *  It parses the response model's content and returned a well structures list of promotional codes or coupons
     * @return List<ContentLineEntity>
     ********************************************************************************************************************************************************/
    @Override
    public List<ContentLineEntity> getContent() {
        processContent();
        return lines;
    }
}
