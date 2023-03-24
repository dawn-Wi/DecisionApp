package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Book;
import com.dawn.decisionApp.repository.BookRepository;
import com.dawn.decisionApp.util;
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
public class BookService {
    private final BookRepository bookRepository;
    private final String kakaoUrl = "https://dapi.kakao.com/v3/search/book";
    private final String kakaoKey = util.kakaoKey.value();

    public Book getRandomBook(){
        List<Book> allBookList = bookRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allBookList.size());
        Book selectBook = allBookList.get(randomIndex);
        if (selectBook.getImage_url() == null) {
            String dramaName = selectBook.getTitle();
            String returnImageUrl = getKakaoImageUrl(dramaName);
            String returnAuthors = getKakaoAuthorsUrl(dramaName);
            selectBook.setImage_url(returnImageUrl);
            selectBook.setAuthors(returnAuthors);
            bookRepository.save(selectBook);
        }
        return selectBook;
    }

    private String getKakaoImageUrl(String researchItem) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + kakaoKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(kakaoUrl)
                .queryParam("query", researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("thumbnail=")[1].split(",")[0];
    }

    private String getKakaoAuthorsUrl(String researchItem) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + kakaoKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(kakaoUrl)
                .queryParam("query", researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("authors=\\[")[1].split("]")[0];
    }
}
