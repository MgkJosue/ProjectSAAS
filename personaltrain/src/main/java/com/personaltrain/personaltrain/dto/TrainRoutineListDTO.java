package com.personaltrain.personaltrain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TrainRoutineListDTO {
    private Long id;
    private String nameRoutine;
    private short timeBegin;
    private short timeEnd; 
    private short numberOfExercises;
    private String description; 
}