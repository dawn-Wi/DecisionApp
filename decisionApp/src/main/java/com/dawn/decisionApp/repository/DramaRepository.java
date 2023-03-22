package com.dawn.decisionApp.repository;

import com.dawn.decisionApp.domain.Drama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DramaRepository extends JpaRepository<Drama, Long> {

}
