//****************************************************************************************************************************************************
// *	Title		:	Prompt
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:   This is the Entity (model/class/object) representing the api_prompt table in the database.
// *                    Part of the Prompt data layer using Java Persistence API, with a MySQL database in this case.
// *=================================================================================================================================================
// *
// *	Dependencies:	MySQL
// *	Usage		:	
// *	Notes		:	
//***************************************************************************************************************************************************

package com.moussdeve.dap.prompt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "api_prompt")
public class Prompt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Prompt title is required")
    @Size(max = 100, message = "Prompt title must be less than 100 characters")
    @Column(name = "prompt_title", nullable = false, length = 100)
    private String promptTitle;

    @NotBlank(message = "Prompt is required")
    @Size(max = 500, message = "Prompt must be less than 500 characters")
    @Column(name = "prompt_text", nullable = false, length = 500)
    private String promptText;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPromptTitle() {
        return promptTitle;
    }
    public void setPromptTitle(String promptTitle) {
        this.promptTitle = promptTitle;
    }

    public String getPromptText() {
        return promptText;
    }
    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }
}
