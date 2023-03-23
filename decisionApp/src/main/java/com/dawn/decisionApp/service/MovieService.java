package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Movie;
import com.dawn.decisionApp.repository.MovieRepository;
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
public class MovieService {
    private final MovieRepository movieRepository;

    private final String imageUrl = "https://dapi.kakao.com/v2/search/image";
    private final String webContentUrl = "https://dapi.kakao.com/v2/search/web";
    private final String key = "824bd5644947b7a1d8f98c8f0037f9ed";

    public Movie getRandomMovie(){
        List<Movie> allMovieList = movieRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allMovieList.size());
        Movie selectMovie = allMovieList.get(randomIndex);
        String movieTitle = selectMovie.getTitle();
        if (selectMovie.getImage_url() == null){
            String returnImageUrl = getImageUrl(movieTitle);
            selectMovie.setImage_url(returnImageUrl);
            movieRepository.save(selectMovie);
        }
        return selectMovie;
    }

    private String getImageUrl(String researchItem) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(imageUrl)
                .queryParam("query", "영화 : "+researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("image_url=")[1].split(",")[0];
    }

}
