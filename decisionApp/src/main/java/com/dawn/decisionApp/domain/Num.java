package com.dawn.decisionApp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "number")
public class Num {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;

    public Num(Integer number) {
        this.number = number;
    }
}
