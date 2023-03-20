package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Food;
import com.dawn.decisionApp.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public List<Food> getFoodList(){
        return foodRepository.findAll();
    }

    public List<Food> getFoodListByCategory(String category){
        return foodRepository.findAllByCategory(category);
    }
}
