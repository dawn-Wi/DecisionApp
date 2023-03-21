package com.dawn.decisionApp.service;

import com.dawn.decisionApp.repository.NumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class NumService {
    private final NumRepository numRepository;

    public int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(1000);
    }

    public int getSingleRandomNumber(){
        Random random = new Random();
        return random.nextInt(100);
    }

    public int getTwoRandomNumber(){
        Random random = new Random();
        return random.nextInt(1000);
    }

    public int getInputRandomNumber(String startNumber, String endNumber){
        Random random = new Random();
        return random.nextInt(Integer.parseInt(startNumber),Integer.parseInt(endNumber)+1);
    }


}
