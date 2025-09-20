//*****************************************************************************************************************************************************************************
// *	Title		:	DeepSeekRequestModel
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

package com.moussdeve.dap.deepseek;

import java.util.List;

public class DeepSeekRequestModel {
    
    private String model;
    private int maxTokens;
    private boolean stream;
    private double temperature;
    private List<Message> messages;

    public DeepSeekRequestModel() {
        // Default constructor
    }

    public DeepSeekRequestModel(String model, List<Message> messages, double temp, int maxTokens, boolean stream) {
        this.model = model;
        this.messages = messages;
        this.temperature = temp;
        this.maxTokens = maxTokens;
        this.stream = stream;
    }

    // Getters and Setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public static class Message {

        private String role;
        private String content;

        public Message(String role, String message) {
            this.role = role;
            this.content = message;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
