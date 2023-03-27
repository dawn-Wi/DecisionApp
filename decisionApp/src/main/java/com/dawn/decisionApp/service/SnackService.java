package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Snack;
import com.dawn.decisionApp.repository.SnackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class SnackService {
    private final SnackRepository snackRepository;
    private final KakaoApiService kakaoApiService;

    public Snack getRandomSnack() {
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
        return kakaoApiService.getImageUrl("과자 : " + researchItem + " " + "회사 : " + researchItem2);
    }
}
