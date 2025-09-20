package com.moussdeve.dap.gpt;

import java.util.ArrayList;

public class GPTRequestModel {
    private String model;                           // The GPT model to use: gpt-4, gpt-4o, or gpt-35-turbo, etc.
    private ArrayList<Message> messages;             // List of messages forming the conversation history
    private float temperature;                      // (Optional/float) controls randomness (0.0 = deterministic, 1.0 = creative)
    private int maxTokens;                          // (Optional/int) Maximum number of tokens in the response

    public GPTRequestModel(String model, ArrayList<Message> messages){
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public float getTemperature() {
        return temperature;
    }
    public void setTemperature(float temp) {
        this.temperature = temp;
    }

    public int getMaxTokens() {
        return maxTokens;
    }
    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }
}

/**
 * Roles:
 *  system: Sets behavior or personality of the assistant
 *  user:   A prompt or question from the user
 *  assistant:  (Optional) Previous model response to maintain context
 */
class Message{
    private String role;
    private String content;

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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }
}