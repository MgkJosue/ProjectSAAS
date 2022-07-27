package com.personaltrain.personaltrain.services;

import java.util.List;

import com.personaltrain.personaltrain.dto.NewTrainRoutineDTO;
import com.personaltrain.personaltrain.dto.TrainRoutineDTO;
import com.personaltrain.personaltrain.dto.TrainRoutineListDTO;

public interface TrainRoutineService {
    public TrainRoutineDTO create(NewTrainRoutineDTO trainRoutienDTO);
    public TrainRoutineDTO retrieve(Long id);
    public TrainRoutineDTO update(TrainRoutineDTO trainRoutineDTO, Long id);
    public void delete(Long id);
    public long count();

    public List<TrainRoutineListDTO> list(int page, int size, String sort);
}
