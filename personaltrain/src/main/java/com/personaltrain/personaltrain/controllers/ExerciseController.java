package com.personaltrain.personaltrain.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personaltrain.personaltrain.dto.ExerciseDTO;
import com.personaltrain.personaltrain.dto.ExerciseTrainRoutineDTO;
import com.personaltrain.personaltrain.dto.NewExerciseDTO;
import com.personaltrain.personaltrain.services.ExerciseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController 
@RequestMapping("/trainRoutines")
public class ExerciseController {
    private final ExerciseService service;
  
    public ExerciseController(ExerciseService srv){
        this.service =srv;
    }

    //============CREATE==============
    @PostMapping("/{id}/exercises")
    public ResponseEntity<ExerciseDTO> create(@PathVariable("id") Long id, @Valid @RequestBody NewExerciseDTO exerciseDTO){
        ExerciseDTO result = service.create(id,exerciseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    //===========RETRIEVE===========
    @GetMapping("/{idTrainRoutine}/exercises/{id}")
    public ResponseEntity<ExerciseTrainRoutineDTO> retrive(@PathVariable("idTrainRoutine") Long idTrainRoutine, @PathVariable("id") Long id){
        ExerciseTrainRoutineDTO result = service.retrieve(idTrainRoutine,id);
        return ResponseEntity.ok().body(result);        
    }

    /*=========UPDATE=========== */
    @PutMapping("/{idTrainRoutine}/exercises/{id}")
    public ResponseEntity<ExerciseDTO> update(@RequestBody ExerciseDTO exerciseDTO,@PathVariable("idTrainRoutine") Long idTrainRoutine ,@PathVariable("id") Long id){
        ExerciseTrainRoutineDTO result = service.update(exerciseDTO, idTrainRoutine,id);
        return ResponseEntity.ok().body(result);
    }

    /*===========DELETE========== */
    @DeleteMapping("/{idTrainRoutine}/exercises/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idTrainRoutine") Long idTrainRoutine, @PathVariable("id") Long id){
        service.delete(idTrainRoutine,id);
        return ResponseEntity.noContent().build();     
    }

     /* ================ LIST ================ */
     @GetMapping("/{id}/exercises")
     public ResponseEntity<List<ExerciseDTO>> list(@PathVariable("id") Long id){
         List<ExerciseDTO> exercises = service.list(id);
         return ResponseEntity.ok().body(exercises);        
     }
    
}
