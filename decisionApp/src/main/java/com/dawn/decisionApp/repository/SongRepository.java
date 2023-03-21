package com.dawn.decisionApp.repository;

import com.dawn.decisionApp.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
    List<Song> findAllByCategory(String category);

    List<Song> findAllByCategoryNotContainsAndCategoryNotContains(String category1, String category2);
}
