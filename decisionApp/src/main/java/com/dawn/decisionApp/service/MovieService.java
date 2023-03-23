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

    private String text = null;

    private final String kakaoImageUrl = "https://dapi.kakao.com/v2/search/image";
    private final String naverUrl = "https://openapi.naver.com/v1/search/movie.json";
    private final String kakaoKey = "824bd5644947b7a1d8f98c8f0037f9ed";
    private final String naverClientId = "yYOf8JMgh876A7kRQDMI";
    private final String naverKey = "52nb8l1Dd6";


    public Movie getRandomMovie() {
        List<Movie> allMovieList = movieRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allMovieList.size());
        Movie selectMovie = allMovieList.get(randomIndex);
        String movieTitle = selectMovie.getTitle();
        if (selectMovie.getImage_url() == null) {
            String returnImageUrl = getKakaoImageUrl(movieTitle);
            String returnActor = getNaverActorUrl(movieTitle);
            selectMovie.setImage_url(returnImageUrl);
            selectMovie.setActor(returnActor);
            movieRepository.save(selectMovie);
        }
        return selectMovie;
    }

    private String getKakaoImageUrl(String researchItem) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + kakaoKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder
                .fromHttpUrl(kakaoImageUrl)
                .queryParam("query", "영화 : " + researchItem)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);
        return result.getBody().get("documents").toString().split("image_url=")[1].split(",")[0];
    }

    private String getNaverActorUrl(String researchItem){
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
        return result.getBody().toString().split("actor=")[1].split(",")[0];
    }

//    private String getNaverUrl(String researchItem) {
//        Map<String, String> requestHeaders = new HashMap<>();
//        requestHeaders.put("X-Naver-Client-Id", naverClientId);
//        requestHeaders.put("X-Naver-Client-Secret", naverKey);
//        String responseBody = get(naverUrl+researchItem,requestHeaders);
//        System.out.println(responseBody);
//        return responseBody;
//    }
//
//
//    @SneakyThrows
//    private String get(String apiUrl, Map<String, String> requestHeaders){
//        HttpURLConnection con = connect(apiUrl);
//        try {
//            con.setRequestMethod("GET");
//            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
//                con.setRequestProperty(header.getKey(), header.getValue());
//            }
//
//            int responseCode = con.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
//                return readBody(con.getInputStream());
//            } else { // 에러 발생
//                return readBody(con.getErrorStream());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("API 요청과 응답 실패", e);
//        } finally {
//            con.disconnect();
//        }
//    }
//
//    private HttpURLConnection connect(String apiUrl){
//        try {
//            URL url = new URL(apiUrl);
//            return (HttpURLConnection)url.openConnection();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
//        } catch (IOException e) {
//            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
//        }
//    }
//
//    private static String readBody(InputStream body){
//        InputStreamReader streamReader = new InputStreamReader(body);
//
//        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
//            StringBuilder responseBody = new StringBuilder();
//
//            String line;
//            while ((line = lineReader.readLine()) != null) {
//                responseBody.append(line);
//            }
//
//            return responseBody.toString();
//        } catch (IOException e) {
//            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
//        }
//    }

}
