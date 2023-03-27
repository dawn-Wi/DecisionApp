package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Food;
import com.dawn.decisionApp.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final KakaoApiService kakaoApiService;

    public Food getRandomFood(){
        List<Food> allFoodList = foodRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allFoodList.size());
        Food selectFood = allFoodList.get(randomIndex);
        String foodName = selectFood.getName();
        if (selectFood.getImage_url() == null){
            String returnImageUrl = getImageUrl(foodName);
            selectFood.setImage_url(returnImageUrl);
            foodRepository.save(selectFood);
        }
        return selectFood;
    }

    public Food getRandomFoodByCategory(String category){
        List<Food> allFoodListByCategory = foodRepository.findAllByCategory(category);
        Random random = new Random();
        int randomIndex = random.nextInt(allFoodListByCategory.size());
        Food selectFood = allFoodListByCategory.get(randomIndex);
        String foodName = selectFood.getName();
        if (selectFood.getImage_url() == null){
            String returnImageUrl = getImageUrl(foodName);
            selectFood.setImage_url(returnImageUrl);
            foodRepository.save(selectFood);
        }
        return selectFood;
    }

    private String getImageUrl(String researchItem) {
        return kakaoApiService.getImageUrl(researchItem);
    }
}
