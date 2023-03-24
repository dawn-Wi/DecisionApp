package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.domain.Snack;
import com.dawn.decisionApp.service.SnackService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SnackController {
    private final SnackService snackService;

    @GetMapping("snack")
    public ResponseEntity<Snack> getRandomSnack(){
        try {
            return ResponseEntity.ok(snackService.getRandomSnack());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
