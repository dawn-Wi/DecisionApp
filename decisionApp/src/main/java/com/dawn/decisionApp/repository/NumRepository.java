package com.dawn.decisionApp.repository;

import com.dawn.decisionApp.domain.Num;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumRepository extends JpaRepository<Num, Long> {

}
