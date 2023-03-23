package com.dawn.decisionApp.repository;

import com.dawn.decisionApp.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    List<Drink> findAllByCategory(String category);
}
