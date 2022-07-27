package com.personaltrain.personaltrain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personaltrain.personaltrain.models.TrainRoutine;

@Repository
public interface TrainRoutineRepository extends JpaRepository<TrainRoutine, Long> {
    
}
