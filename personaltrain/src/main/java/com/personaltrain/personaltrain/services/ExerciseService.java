package com.personaltrain.personaltrain.services;

import java.util.List;

import com.personaltrain.personaltrain.dto.ExerciseDTO;
import com.personaltrain.personaltrain.dto.ExerciseTrainRoutineDTO;
import com.personaltrain.personaltrain.dto.NewExerciseDTO;

public interface ExerciseService {
    public ExerciseDTO create(Long idExercise, NewExerciseDTO exerciseDTO);
    public ExerciseTrainRoutineDTO retrieve(Long idTrainRoutine, Long id);
    public ExerciseTrainRoutineDTO update(ExerciseDTO exerciseDTO, Long idTrainRotuine, Long id);
    public void delete(Long idExercise, Long id);

    public List<ExerciseDTO> list(Long idTrainRoutine);
}
