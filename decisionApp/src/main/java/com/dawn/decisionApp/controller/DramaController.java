package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.domain.Drama;
import com.dawn.decisionApp.service.DramaService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DramaController {
    private final DramaService dramaService;

    @GetMapping("drama")
    public ResponseEntity<Drama> getRandomDrama(){
        try {
            return ResponseEntity.ok(dramaService.getRandomDrama());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
