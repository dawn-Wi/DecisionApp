package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.service.ColorService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ColorController {
    private final ColorService colorService;

    @GetMapping("color")
    public ResponseEntity<List<Integer>> getRandomColor(){
        try{
            return ResponseEntity.ok(colorService.getRandomColor());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
