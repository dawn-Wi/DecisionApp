package com.dawn.decisionApp.service;

import com.dawn.decisionApp.domain.Book;
import com.dawn.decisionApp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final KakaoApiService kakaoApiService;

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
        return kakaoApiService.getThumbnailUrl(researchItem);
    }

    private String getKakaoAuthorsUrl(String researchItem) {
        return kakaoApiService.getAuthorsUrl(researchItem);
    }
}
