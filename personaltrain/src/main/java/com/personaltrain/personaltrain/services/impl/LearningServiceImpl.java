package com.personaltrain.personaltrain.services.impl;

import com.personaltrain.personaltrain.dto.LearningDTO;
import com.personaltrain.personaltrain.dto.NewLearningDTO;
import com.personaltrain.personaltrain.exceptions.ResourceNotFoundException;
import com.personaltrain.personaltrain.models.Learning;
import com.personaltrain.personaltrain.repositories.LearningRepository;
import com.personaltrain.personaltrain.services.LearningService;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LearningServiceImpl implements LearningService{
    final ModelMapper modelMapper;
    final LearningRepository learningRepository;

    @Autowired
    public LearningServiceImpl(@Autowired LearningRepository repository, ModelMapper mapper){
        this.learningRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public LearningDTO create(NewLearningDTO learningDTO) {
        Learning learning = modelMapper.map(learningDTO, Learning.class);
        learningRepository.save(learning);        
        return modelMapper.map(learning,  LearningDTO.class); 
    }
    
    @Override
    @Transactional(readOnly = true)
    public LearningDTO retrieve(Long id) {
        Learning learning = learningRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Routine learning not found"));
        return modelMapper.map(learning, LearningDTO.class);
    }

    @Override
    @Transactional
    public LearningDTO update(LearningDTO learningDTO, Long id) {
        Learning learning = learningRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Routine learning not found"));
        
        learning.setId(id);
        learning = modelMapper.map(learningDTO, Learning.class);
        learningRepository.save(learning);       

        return modelMapper.map(learning, LearningDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Learning learning = learningRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Routine learning  not found"));        
        learningRepository.deleteById(learning.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<LearningDTO> list() {
        List<Learning> learnings = learningRepository.findAll();
        return learnings.stream().map(learning -> modelMapper.map(learning, LearningDTO.class))
            .collect(Collectors.toList());        
    }
}
