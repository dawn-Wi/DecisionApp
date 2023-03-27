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
    private final KakaoApiService kakaoApiService;

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
        return kakaoApiService.getImageUrl("드라마 : " + researchItem);
    }
}
