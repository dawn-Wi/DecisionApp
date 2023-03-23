package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.domain.Drink;
import com.dawn.decisionApp.service.DrinkService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DrinkController {
    private final DrinkService drinkService;

    @GetMapping("drink")
    public ResponseEntity<Drink> getRandomDrink(){
        try {
            return ResponseEntity.ok(drinkService.getRandomDrink());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("drink/category")
    public ResponseEntity<Drink> getRandomDrinkByCategory(@RequestParam("category")String category){
        try {
            return ResponseEntity.ok(drinkService.getRandomDrinkByCategory(category));
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
