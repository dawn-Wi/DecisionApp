package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.domain.Song;
import com.dawn.decisionApp.service.SongService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SongController {
    private final SongService songService;

    @GetMapping("song")
    public ResponseEntity<Song> getRandomSong(){
        try{
            return ResponseEntity.ok(songService.getRandomSong());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("song/category")
    public ResponseEntity<Song> getRandomSongByCategory(@RequestParam("category")String category){
        try{
            return ResponseEntity.ok(songService.getRandomSongByCategory(category));
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("song/category/other")
    public ResponseEntity<Song> getRandomSongByCategoryIsOther(){
        try {
            return ResponseEntity.ok(songService.getRandomSongByCategoryIsOther());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
