package com.personaltrain.personaltrain.services.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personaltrain.personaltrain.dto.ExerciseDTO;
import com.personaltrain.personaltrain.dto.ExerciseTrainRoutineDTO;
import com.personaltrain.personaltrain.dto.NewExerciseDTO;
import com.personaltrain.personaltrain.exceptions.NoContentException;
import com.personaltrain.personaltrain.exceptions.ResourceNotFoundException;
import com.personaltrain.personaltrain.models.Exercise;
import com.personaltrain.personaltrain.models.TrainRoutine;
import com.personaltrain.personaltrain.repositories.ExerciseRepository;
import com.personaltrain.personaltrain.repositories.TrainRoutineRepository;
import com.personaltrain.personaltrain.services.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    final ModelMapper modelMapper;
    final ExerciseRepository repository;
    final TrainRoutineRepository trainRoutineRepository;

    public ExerciseServiceImpl(ExerciseRepository r, TrainRoutineRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.trainRoutineRepository = er;
    }


    @Override
    @Transactional
    public ExerciseDTO create(Long idTrainRoutine, NewExerciseDTO exerciseDTO) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(idTrainRoutine)
            .orElseThrow(()-> new ResourceNotFoundException("Train Routine not found"));
        Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);    
        exercise.setTrainRoutine(trainRoutine);
        repository.save(exercise);
        return modelMapper.map(exercise, ExerciseDTO.class); 
    }

    @Override
    @Transactional(readOnly=true)
    public ExerciseTrainRoutineDTO retrieve(Long idTrainRoutine, Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(idTrainRoutine)
            .orElseThrow(()-> new ResourceNotFoundException("Train Routine not found"));
        Exercise exercise = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Exercise not found"));
        exercise.setTrainRoutine(trainRoutine);
        return modelMapper.map(exercise, ExerciseTrainRoutineDTO.class);
    }

    @Override
    @Transactional
    public ExerciseTrainRoutineDTO update(ExerciseDTO exerciseDTO, Long idTrainRoutine, Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(idTrainRoutine)
        .orElseThrow(()-> new ResourceNotFoundException("Train Routine not found"));
        Exercise exercise = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Exercise not found"));
        exercise.setTrainRoutine(trainRoutine);
        return modelMapper.map(exercise, ExerciseTrainRoutineDTO.class);
    }


    @Override
    @Transactional
    public void delete(Long idTrainRoutine, Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(idTrainRoutine)
        .orElseThrow(()-> new ResourceNotFoundException("Train Routine not found"));
        Exercise exercise = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Exercise not found"));
        exercise.setTrainRoutine(trainRoutine);
        repository.deleteById(exercise.getId());  
    }

    @Override
    @Transactional(readOnly=true)
    public List<ExerciseDTO> list(Long idTrainRoutine){
         TrainRoutine trainRoutine= trainRoutineRepository.findById(idTrainRoutine)
            .orElseThrow(()-> new ResourceNotFoundException("Train Routine not found"));
        List<Exercise> exercises = repository.findByTrainRoutine(trainRoutine);
        if(exercises.isEmpty()) throw new NoContentException("Exercises is empty");
        //Lambda ->
        return exercises.stream().map(q -> modelMapper.map(q, ExerciseDTO.class) )
            .collect(Collectors.toList());
    }
   
}
                             