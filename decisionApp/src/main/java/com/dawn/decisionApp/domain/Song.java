package com.dawn.decisionApp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String singer;
    private Date releaseDate;
    private String category;
    private String image_url;

    public Song(String title, String singer, Date releaseDate, String category,String image_url){
        this.title = title;
        this.singer = singer;
        this.releaseDate = releaseDate;
        this.category = category;
        this.image_url = image_url;
    }
}
