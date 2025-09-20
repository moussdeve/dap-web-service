//*****************************************************************************************************************************************************************************
// * 
// * 
// * 
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.globalprocess;

import java.util.List;

public class ContentLineEntity {
    private String code;
    private String type;
    private String summary;
    private String discount;

    public ContentLineEntity() {}

    public ContentLineEntity(String code, String type, String discount, String summary) {
        this.code = code;
        this.type = type;
        this.summary = summary;
        this.discount = discount;
    }

    public ContentLineEntity(List<String> members) {
        if (members.size() >= 4) {
            this.code = members.get(0);
            this.discount = members.get(1);
            this.summary = members.get(2);
            this.type = members.get(3);
        }
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }    
}
