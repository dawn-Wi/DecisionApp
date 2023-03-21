package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.service.NumService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NumController {
    private final NumService numService;

    @GetMapping("num")
    public ResponseEntity<Integer> getRandomNumber(){
        try{
            return ResponseEntity.ok(numService.getRandomNumber());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("num/single")
    public ResponseEntity<Integer> getSingleRandomNumber(){
        try{
            return ResponseEntity.ok(numService.getSingleRandomNumber());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("num/two")
    public ResponseEntity<Integer> getTwoRandomNumber(){
        try{
            return ResponseEntity.ok(numService.getTwoRandomNumber());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("num/input")
    public ResponseEntity<Integer> getInputRandomNumber(String startNumber, String endNumber){
        try{
            return ResponseEntity.ok(numService.getInputRandomNumber(startNumber, endNumber));
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
