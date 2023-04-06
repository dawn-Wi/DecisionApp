package com.dawn.decisionApp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image_url;
    private String authors;
    private String contents;

    public Book(String title, String image_url, String authors,String contents){
        this.title = title;
        this.image_url = image_url;
        this.authors = authors;
        this.contents = contents;
    }
}
