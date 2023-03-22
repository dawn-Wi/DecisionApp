package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Drama;
import com.dawn.decisionApp.repository.DramaRepository;
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
public class DramaService {
    private final DramaRepository dramaRepository;
    private final String url = "https://dapi.kakao.com/v2/search/image";
    private final String key = "824bd5644947b7a1d8f98c8f0037f9ed";

    public Drama getRandomDrama() {
        List<Drama> allDramaList = dramaRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allDramaList.size());
        Drama selectDrama = allDramaList.get(randomIndex);
        if (selectDrama.getImage_url() == null) {
            String dramaName = selectDrama.getName();
            String returnUrl = getUrl(dramaName);
            selectDrama.setImage_url(returnUrl);
            dramaRepository.save(selectDrama);
        }
        return selectDrama;
    }

    private String getUrl(String researchItem) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("query", "드라마 : "+researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("image_url=")[1].split(",")[0];
    }

}
