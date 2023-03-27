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
    private final String url = "https://dapi.kakao.com/v2/search/image";
    private final String key = util.kakaoKey.value();

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
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("query", "음료 : "+researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("image_url=")[1].split(",")[0];
    }

}
