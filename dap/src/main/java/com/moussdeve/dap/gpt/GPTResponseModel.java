//*****************************************************************************************************************************************************************************
// * 
// * 
// * 
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.gpt;

import java.util.List;

public class GPTResponseModel {
    private String id;                      // Unique identifier for the request
    private String object;                  // Always 'chat.completion'
    private int created;                    // Unix timestamp of the response
    private String model;                   // Model name used
    private List<String> choices;           // List of completion results (typically 1 unless n > 1)
    private Object message;                 // The assistant's reply
    private String finishReason;            // Why the completion stopped (stop, length, etc.)
    private Object usage;                   // Token usage stats for billing and monitoring

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }

    public int getCreated() {
        return created;
    }
    public void setCreated(int created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public List<String> getChoices() {
        return choices;
    }
    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }

    public String getFinishReason() {
        return finishReason;
    }
    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
    
    public Object getUsage() {
        return usage;
    }
    public void setUsage(Object usage) {
        this.usage = usage;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((object == null) ? 0 : object.hashCode());
        result = prime * result + created;
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((choices == null) ? 0 : choices.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((finishReason == null) ? 0 : finishReason.hashCode());
        result = prime * result + ((usage == null) ? 0 : usage.hashCode());
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
        GPTResponseModel other = (GPTResponseModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (object == null) {
            if (other.object != null)
                return false;
        } else if (!object.equals(other.object))
            return false;
        if (created != other.created)
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (choices == null) {
            if (other.choices != null)
                return false;
        } else if (!choices.equals(other.choices))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (finishReason == null) {
            if (other.finishReason != null)
                return false;
        } else if (!finishReason.equals(other.finishReason))
            return false;
        if (usage == null) {
            if (other.usage != null)
                return false;
        } else if (!usage.equals(other.usage))
            return false;
        return true;
    }
}
