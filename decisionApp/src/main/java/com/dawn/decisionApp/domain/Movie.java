package com.dawn.decisionApp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image_url;
    private String category;
    private String actor;

    public Movie(String title, String image_url, String category, String actor){
        this.title = title;
        this.image_url = image_url;
        this.category = category;
        this.actor = actor;
    }
}
