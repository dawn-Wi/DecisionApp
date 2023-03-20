package com.dawn.decisionApp.repository;

import com.dawn.decisionApp.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findFoodsByCategory(String category);
    List<Food> findAllByCategory(String category);
}
