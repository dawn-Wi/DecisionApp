package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Song;
import com.dawn.decisionApp.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class SongService {
    private final SongRepository songRepository;

    public Song getRandomSong(){
        List<Song> allSongList = songRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allSongList.size());
        return allSongList.get(randomIndex);
    }

    public Song getRandomSongByCategory(String category){
        List<Song> songListByCategory = songRepository.findAllByCategory(category);
        Random random = new Random();
        int randomIndex = random.nextInt(songListByCategory.size());
        return songListByCategory.get(randomIndex);
    }

    public Song getRandomSongByCategoryIsOther(){
        List<Song> songListByCategoryIsOther = songRepository.findAllByCategoryNotContainsAndCategoryNotContains("댄스", "발라드");
        Random random = new Random();
        int randomIndex = random.nextInt(songListByCategoryIsOther.size());
        return songListByCategoryIsOther.get(randomIndex);
    }

}
