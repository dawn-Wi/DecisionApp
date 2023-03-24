package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Food;
import com.dawn.decisionApp.repository.FoodRepository;
import com.dawn.decisionApp.util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    private final String imageUrl = "https://dapi.kakao.com/v2/search/image";
    private final String key = util.kakaoKey.value();

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
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(imageUrl)
                .queryParam("query", researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("image_url=")[1].split(",")[0];
    }
}
