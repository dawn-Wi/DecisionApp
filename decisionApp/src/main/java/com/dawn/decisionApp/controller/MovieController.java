package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.domain.Movie;
import com.dawn.decisionApp.service.MovieService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MovieController {
    private final MovieService movieService;

    @GetMapping("movie")
    public ResponseEntity<Movie> getRandomMovie(){
        try{
            return ResponseEntity.ok(movieService.getRandomMovie());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
