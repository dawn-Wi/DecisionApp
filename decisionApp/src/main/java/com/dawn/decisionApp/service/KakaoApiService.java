package com.dawn.decisionApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.context.IContext;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class KakaoApiService {
    private final Environment env;
    private final String url = "https://dapi.kakao.com/v2/search/image";
//    private final String key = env.getProperty("kakao.api.key");

    @Value("${kakao.api.key}")
    private String key;
    public String getImageUrl(String queryString){
        return getUrl(queryString, "image_url=", ",");
    }

    public String getThumbnailUrl(String queryString){
        return getUrl(queryString, "thumbnail=", ",");
    }

    public String getAuthorsUrl(String queryString){
        return getUrl(queryString, "authors=\\[", "]");
    }

    private String getUrl(String queryString, String split1Regex, String split2Regex){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        System.out.println(httpHeaders);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("query", queryString)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        String responseString = result.getBody().get("documents").toString();
        if(responseString.length() < 3)
            return "";
        return result.getBody().get("documents").toString().split(split1Regex)[1].split(split2Regex)[0];
    }
}
