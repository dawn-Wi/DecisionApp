package com.dawn.decisionApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class ColorService {
    public List<Integer> getRandomColor(){
        List<Integer> returnList = new ArrayList<>();
        Random randomR = new Random();
        Random randomG = new Random();
        Random randomB = new Random();
        returnList.add(randomR.nextInt(255));
        returnList.add(randomG.nextInt(255));
        returnList.add(randomB.nextInt(255));
        return returnList;
    }
}
