//*****************************************************************************************************************************************************************************
// * 
// * 
// * 
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.globalprocess;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "api_config")
public class ApiConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "LLM name is required")
    @Size(max = 100, message = "LLM name must be less than 100 characters")
    @Column(name = "llm_name", nullable = false, length = 100)
    private String llmName;

    @NotBlank(message = "URI is required")
    @Size(max = 500, message = "URI must be less than 500 characters")
    @Column(name = "uri", nullable = false, length = 500)
    private String uri;

    @NotBlank(message = "Token is required")
    @Size(max = 500, message = "Token must be less than 500 characters")
    @Column(name = "token", nullable = false, length = 500)
    private String token;

    public ApiConfig() {}

    public ApiConfig(String llname, String uri, String token) {
        this.uri = uri;
        this.token = token;
        this.llmName = llname;
    }

    // Getters and Setters for all members
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUri() { return this.uri; };
    public void setUri(String uri) { this.uri = uri; }

    public String getToken() { return this.token; }
    public void setToken(String token) { this.token = token; }

    public String getLlmName() { return this.llmName; }
    public void setLlmName(String llmName) { this.llmName = llmName; }
}
