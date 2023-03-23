package com.dawn.decisionApp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "drink")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image_url;
    private String category;
    private String manufacturingCompany;

    public Drink(String name, String image_url, String category, String manufacturingCompany){
        this.name = name;
        this.image_url = image_url;
        this.category = category;
        this.manufacturingCompany = manufacturingCompany;
    }
}
