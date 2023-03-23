package com.dawn.decisionApp.controller;

import com.dawn.decisionApp.domain.Book;
import com.dawn.decisionApp.service.BookService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @GetMapping("book")
    public ResponseEntity<Book> getRandomBook(){
        try {
            return ResponseEntity.ok(bookService.getRandomBook());
        }catch (EntityExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
