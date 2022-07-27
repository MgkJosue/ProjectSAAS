package com.personaltrain.personaltrain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personaltrain.personaltrain.models.Learning;


@Repository
public interface LearningRepository extends JpaRepository <Learning,Long> {
    
}
