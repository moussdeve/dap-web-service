package com.moussdeve.dap.prompt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiPromptRepository extends JpaRepository<Prompt, Long> {
    // add custom methods here
}
