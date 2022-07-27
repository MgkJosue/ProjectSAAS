package com.personaltrain.personaltrain.dto;


import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class NewExerciseDTO {
    @NotNull(message = "Name exercise can't be null")
    private String name;
    private String muscularGroup;
    private String description;
    private TrainRoutineDTO trainRoutineDTO;
}
