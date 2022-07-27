package com.personaltrain.personaltrain.services;

import java.util.List;

import com.personaltrain.personaltrain.dto.LearningDTO;
import com.personaltrain.personaltrain.dto.NewLearningDTO;

public interface LearningService {
    public LearningDTO create(NewLearningDTO learningDTO);
    public LearningDTO retrieve(Long id);
    public LearningDTO update(LearningDTO learningDTO, Long id);
    public void delete(Long id);

    public List<LearningDTO> list();
}
