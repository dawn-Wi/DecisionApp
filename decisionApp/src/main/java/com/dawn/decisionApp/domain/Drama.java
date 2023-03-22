package com.dawn.decisionApp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "drama")
public class Drama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image_url;
    private String category;

    public Drama(String name, String image_url, String category){
        this.name = name;
        this.image_url = image_url;
        this.category = category;
    }
}
