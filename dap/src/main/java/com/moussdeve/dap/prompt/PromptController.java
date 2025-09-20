//*****************************************************************************************************************************************************************************
// *	Title		:	promptController
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:	This class implements the RestController exposing Prompt data handling HTTP requests and responses in this RESTful web service (API).
// *                        - Receives HTTP requests (GET, POST, PUT, and DELETE)
// *                        - Process the requests (calls the Prompt service and computes besiness logic)
// *                        - Returns a response  in JSON format.
// *
// *                    Features:
// *                        Routing - Maps HTTP methods and URLs to specific function
// *                        CRUD Operations - Supports the Create, Read, Update, and Delete operations
// *                        Statelessness - Each request is independent; does not store any session state
// *                        Prompt-Resource-Oriented - Operates on Prompt resources
// *==========================================================================================================================================================================
// *
// *	Dependencies:	NONE
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************


package com.moussdeve.dap.prompt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/dap/api/v1.0/prompt/")
public class PromptController {

    @Autowired
    private ApiPromptService promptService;

    @GetMapping
    public List<Prompt> getAllPrompts() {
        return promptService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prompt> getPrompt(@PathVariable Long id) {
        Optional<Prompt> prompt = promptService.findById(id);
        return prompt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{title}")
    public ResponseEntity<Prompt> getPrompt(@PathVariable String title) {
        Optional<Prompt> prompt = promptService.findByTitle(title);
        return prompt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Prompt> createConfig(@Valid @RequestBody Prompt prompt) {
        Prompt savedPrompt = promptService.save(prompt);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrompt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prompt> updatePrompt(@PathVariable Long id, @RequestBody Prompt updatedPrompt) {
        return promptService.findById(id).map(prompt ->
        {
            prompt.setPromptTitle(updatedPrompt.getPromptTitle());
            prompt.setPromptText(updatedPrompt.getPromptText());
            return ResponseEntity.ok(promptService.save(prompt));
        })
        .orElse(ResponseEntity.notFound().build());
    }

    // Delete a prompt
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (promptService.exists(id)) {
            promptService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
