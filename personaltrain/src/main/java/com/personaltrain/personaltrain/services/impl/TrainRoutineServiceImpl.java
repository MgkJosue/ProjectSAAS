package com.personaltrain.personaltrain.services.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personaltrain.personaltrain.dto.NewTrainRoutineDTO;
import com.personaltrain.personaltrain.dto.TrainRoutineDTO;
import com.personaltrain.personaltrain.dto.TrainRoutineListDTO;
import com.personaltrain.personaltrain.exceptions.NoContentException;
import com.personaltrain.personaltrain.exceptions.ResourceNotFoundException;
import com.personaltrain.personaltrain.models.TrainRoutine;
import com.personaltrain.personaltrain.repositories.TrainRoutineRepository;
import com.personaltrain.personaltrain.services.TrainRoutineService;


@Service
public class TrainRoutineServiceImpl implements TrainRoutineService {

    final ModelMapper modelMapper;
    final TrainRoutineRepository trainRoutineRepository;

    public TrainRoutineServiceImpl(TrainRoutineRepository repository, ModelMapper mapper){
        this.trainRoutineRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public TrainRoutineDTO create(NewTrainRoutineDTO trainRoutineDTO) {
        TrainRoutine trainRoutine = modelMapper.map(trainRoutineDTO, TrainRoutine.class);
        trainRoutineRepository.save(trainRoutine);        
        return modelMapper.map(trainRoutine, TrainRoutineDTO.class); 
    }

    @Override
    @Transactional(readOnly = true)
    public TrainRoutineDTO retrieve(Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Train Routine not found"));
        return modelMapper.map(trainRoutine, TrainRoutineDTO.class);
    }

    @Override
    @Transactional
    public TrainRoutineDTO update(TrainRoutineDTO trainRoutineDTO, Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Train Routine not found"));        
              
        TrainRoutine trainRoutineUpdated = modelMapper.map(trainRoutineDTO, TrainRoutine.class);
        //Keeping values
        trainRoutineUpdated.setCreatedBy(trainRoutine.getCreatedBy());
        trainRoutineUpdated.setCreatedDate(trainRoutine.getCreatedDate());
        trainRoutineRepository.save(trainRoutineUpdated);   
        return modelMapper.map(trainRoutineUpdated, TrainRoutineDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Train Routine not found"));        
        trainRoutineRepository.deleteById(trainRoutine.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainRoutineListDTO> list(int page, int size, String sort) {
        Pageable pageable = sort == null || sort.isEmpty() ? 
                    PageRequest.of(page, size) 
                :   PageRequest.of(page, size,  Sort.by(sort));

        Page<TrainRoutine> trainRoutines = trainRoutineRepository.findAll(pageable);
        if(trainRoutines.isEmpty()) throw new NoContentException("Train Routine is empty");
        return trainRoutines.stream().map(trainRoutine -> modelMapper.map(trainRoutine, TrainRoutineListDTO.class))
            .collect(Collectors.toList());        
    }

    @Override
    public long count() {        
        return trainRoutineRepository.count();
    }
    
}


