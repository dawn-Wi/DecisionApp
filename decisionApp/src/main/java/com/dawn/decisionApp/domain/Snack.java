package com.dawn.decisionApp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "snack")
public class Snack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image_url;
    private String manufacturingCompany;

    public Snack(String name, String image_url, String manufacturingCompany){
        this.name = name;
        this.image_url = image_url;
        this.manufacturingCompany = manufacturingCompany;
    }
}
