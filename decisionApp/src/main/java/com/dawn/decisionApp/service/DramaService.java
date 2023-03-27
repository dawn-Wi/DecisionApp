package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Drama;
import com.dawn.decisionApp.repository.DramaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class DramaService {
    private final DramaRepository dramaRepository;
    private final String kakaoUrl = "https://dapi.kakao.com/v2/search/image";
    private final String kakaoKey = util.kakaoKey.value();

    public Drama getRandomDrama() {
        List<Drama> allDramaList = dramaRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allDramaList.size());
        Drama selectDrama = allDramaList.get(randomIndex);
        if (selectDrama.getImage_url() == null) {
            String dramaName = selectDrama.getName();
            String returnUrl = getKakaoUrl(dramaName);
            selectDrama.setImage_url(returnUrl);
            dramaRepository.save(selectDrama);
        }
        return selectDrama;
    }

    private String getKakaoUrl(String researchItem) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + kakaoKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(kakaoUrl)
                .queryParam("query", "드라마 : "+researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("image_url=")[1].split(",")[0];
    }
}
