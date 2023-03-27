package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Drink;
import com.dawn.decisionApp.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final KakaoApiService kakaoApiService;

    public Drink getRandomDrink(){
        List<Drink> allDrinkList = drinkRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allDrinkList.size());
        Drink selectDrink = allDrinkList.get(randomIndex);
        if (selectDrink.getImage_url() == null) {
            String drinkName = selectDrink.getName();
            String returnUrl = getUrl(drinkName);
            selectDrink.setImage_url(returnUrl);
            drinkRepository.save(selectDrink);
        }
        return selectDrink;
    }

    public Drink getRandomDrinkByCategory(String category){
        List<Drink> allDrinkListByCategory = drinkRepository.findAllByCategory(category);
        Random random = new Random();
        int randomIndex = random.nextInt(allDrinkListByCategory.size());
        Drink selectDrink = allDrinkListByCategory.get(randomIndex);
        if (selectDrink.getImage_url() == null) {
            String drinkName = selectDrink.getName();
            String returnUrl = getUrl(drinkName);
            selectDrink.setImage_url(returnUrl);
            drinkRepository.save(selectDrink);
        }
        return selectDrink;
    }

    private String getUrl(String researchItem) {
        return kakaoApiService.getImageUrl("음료 : "+researchItem);
    }
}
