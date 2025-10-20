//**************************************************************************************************************************************
// *	Title		:	ApiPromptService
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// *
// * 	Description	:	This class implements calls to the repository supporting CRUD operations to on the database
// *====================================================================================================================================
// *
// *	Dependencies:	
// *	Usage		:	
// *	Notes		:	
//**************************************************************************************************************************************

package com.moussdeve.dap.prompt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiPromptService {

    @Autowired
    private ApiPromptRepository promptRepository;

    public List<Prompt> findAll() {
        return promptRepository.findAll();
    }

    public Optional<Prompt> findById(Long id) {
        return promptRepository.findById(id);
    }

    public Optional<Prompt> findByTitle(String title) {
        return findAll().stream().filter(prompt -> 
            (prompt.getPromptTitle().equalsIgnoreCase(title))).findFirst();
    }

    public Prompt save(Prompt prompt) {
        Prompt savedPrompt = promptRepository.save(prompt);
        return savedPrompt;
    }

    public Prompt update(Long id, Prompt updatedPrompt) {
        return promptRepository.findById(id).map(prt ->
        {
            prt.setPromptTitle(updatedPrompt.getPromptTitle());
            prt.setPromptText(updatedPrompt.getPromptText());
            return promptRepository.save(prt);
        })
        .orElse(new Prompt());
    }

    public boolean delete(Long id) {
        if (promptRepository.existsById(id)) {
            promptRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean delete(String title) {
        Optional<Prompt> prompt = findByTitle(title);
        if (prompt.isPresent()) {
            promptRepository.delete(prompt.get());
            return true;
        }
        return false;
    }

    public boolean delete(Prompt prompt) {
        if (promptRepository.existsById(prompt.getId())) {
            promptRepository.delete(prompt);
            return true;
        }
        return false;
    }

    public boolean exists(Long id) {
        return promptRepository.existsById(id);
    }

    public boolean exists(Prompt prompt) {
        return promptRepository.existsById(prompt.getId());
    }

    public boolean exists(String title) {
        return exists(findByTitle(title).get());
    }

}
