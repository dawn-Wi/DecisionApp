package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Snack;
import com.dawn.decisionApp.repository.SnackRepository;
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
public class SnackService {
    private final SnackRepository snackRepository;
    private final String url = "https://dapi.kakao.com/v2/search/image";
    private final String key = util.kakaoKey.value();

    public Snack getRandomSnack(){
        List<Snack> allSnackList = snackRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allSnackList.size());
        Snack selectSnack = allSnackList.get(randomIndex);
        if (selectSnack.getImage_url() == null) {
            String snackName = selectSnack.getName();
            String snackCompany = selectSnack.getManufacturingCompany();
            String returnUrl = getUrl(snackName, snackCompany);
            selectSnack.setImage_url(returnUrl);
            snackRepository.save(selectSnack);
        }
        return selectSnack;
    }

    private String getUrl(String researchItem, String researchItem2) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("query", "과자 : "+researchItem)
                .queryParam("query", "회사 : "+researchItem2)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("image_url=")[1].split(",")[0];
    }
}
