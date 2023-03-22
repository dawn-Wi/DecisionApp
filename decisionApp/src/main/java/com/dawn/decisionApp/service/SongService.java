package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Song;
import com.dawn.decisionApp.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class SongService {
    private final SongRepository songRepository;
    private final String url = "https://dapi.kakao.com/v2/search/image";
    private final String key = "824bd5644947b7a1d8f98c8f0037f9ed";

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
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("query", researchItem+"앨범 커버 이미지")
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("image_url=")[1].split(",")[0];
    }

}
