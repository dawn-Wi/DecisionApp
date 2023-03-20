package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.domain.Food;
import com.dawn.decisionApp.service.FoodService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @GetMapping("food")
    public ResponseEntity<List<Food>> getFoodList(){
        try {
            return ResponseEntity.ok(foodService.getFoodList());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("food/category")
    public ResponseEntity<List<Food>> getFoodListByCategory(@RequestParam("category")String category){
        try{
            return ResponseEntity.ok(foodService.getFoodListByCategory(category));
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
