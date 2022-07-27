package com.personaltrain.personaltrain.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class NewTrainRoutineDTO {
    @NotNull(message = "Name Routine can't be null")
    private String nameRoutine;
    private short timeBegin;
    private short timeEnd;
    private short numberOfExercises;
    private String description;
}
