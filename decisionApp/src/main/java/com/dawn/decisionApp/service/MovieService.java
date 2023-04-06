package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Movie;
import com.dawn.decisionApp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
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
    private final KakaoApiService kakaoApiService;
    private final String naverUrl = "https://openapi.naver.com/v1/search/movie.json";
    @Value("${naver.client.id}")
    private String naverClientId;
    @Value("${naver.api.key}")
    private String naverKey;

    public Movie getRandomMovie() {
        List<Movie> allMovieList = movieRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allMovieList.size());
        Movie selectMovie = allMovieList.get(randomIndex);
        String movieTitle = selectMovie.getTitle();
        if (selectMovie.getImage_url() == null) {
            String returnImageUrl = getKakaoImageUrl(movieTitle);
            String returnActor = getNaverActorUrl(movieTitle, "actor=",",");
            selectMovie.setImage_url(returnImageUrl);
            selectMovie.setActor(returnActor);
            movieRepository.save(selectMovie);
        }
        return selectMovie;
    }

    private String getKakaoImageUrl(String researchItem) {
        return kakaoApiService.getImageUrl("영화 : "+researchItem);
    }

    private String getNaverActorUrl(String researchItem, String split1Regex, String split2Regex){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverClientId);
        httpHeaders.add("X-Naver-Client-Secret", naverKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(naverUrl)
                .queryParam("query", researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        System.out.println(result.getBody().toString());
        return result.getBody().toString().split(split1Regex)[1].split(split2Regex)[0];
    }
}
