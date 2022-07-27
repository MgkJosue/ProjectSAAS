package com.personaltrain.personaltrain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personaltrain.personaltrain.models.Exercise;
import com.personaltrain.personaltrain.models.TrainRoutine;



@Repository
public interface ExerciseRepository extends JpaRepository <Exercise,Long> {

    public List<Exercise> findByTrainRoutine(TrainRoutine trainRoutine);
}
