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
    private final KakaoApiService kakaoApiService;

    public Song getRandomSong(){
        List<Song> allSongList = songRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allSongList.size());
        Song selectSong = allSongList.get(randomIndex);
        if (selectSong.getImage_url() == null){
            String songTitle = selectSong.getTitle();
            String returnUrl = getUrl(songTitle);
            selectSong.setImage_url(returnUrl);
            songRepository.save(selectSong);
        }
        return selectSong;
    }

    public Song getRandomSongByCategory(String category){
        List<Song> songListByCategory = songRepository.findAllByCategory(category);
        Random random = new Random();
        int randomIndex = random.nextInt(songListByCategory.size());
        Song selectSong = songListByCategory.get(randomIndex);
        if (selectSong.getImage_url() == null){
            String songTitle = selectSong.getTitle();
            String returnUrl = getUrl(songTitle);
            selectSong.setImage_url(returnUrl);
            songRepository.save(selectSong);
        }
        return selectSong;
    }

    public Song getRandomSongByCategoryIsOther(){
        List<Song> songListByCategoryIsOther = songRepository.findAllByCategoryNotContainsAndCategoryNotContains("댄스", "발라드");
        Random random = new Random();
        int randomIndex = random.nextInt(songListByCategoryIsOther.size());
        Song selectSong = songListByCategoryIsOther.get(randomIndex);
        if (selectSong.getImage_url() == null){
            String songTitle = selectSong.getTitle();
            String returnUrl = getUrl(songTitle);
            selectSong.setImage_url(returnUrl);
            songRepository.save(selectSong);
        }
        return selectSong;
    }

    private String getUrl(String researchItem) {
        return kakaoApiService.getImageUrl("노래 : "+ researchItem);
    }
}