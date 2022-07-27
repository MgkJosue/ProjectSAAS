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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personaltrain.personaltrain.dto.NewTrainRoutineDTO;
import com.personaltrain.personaltrain.dto.TrainRoutineDTO;
import com.personaltrain.personaltrain.dto.TrainRoutineListDTO;
import com.personaltrain.personaltrain.services.TrainRoutineService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/trainRoutines")
public class TrainRoutineController {
    private final TrainRoutineService service;
  
    public TrainRoutineController(TrainRoutineService srv){
        this.service =srv;
    }
    
    /* ================ CREATE ================ */
    @PostMapping()
    public ResponseEntity<TrainRoutineDTO> create(@Valid @RequestBody NewTrainRoutineDTO trainRoutineDTO){
        TrainRoutineDTO result = service.create(trainRoutineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{id}")
    public ResponseEntity<TrainRoutineDTO> retrive(@PathVariable("id") Long id){
        TrainRoutineDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }


    /* ================ UPDATE ================ */
    @PutMapping("/{id}")
    public ResponseEntity<TrainRoutineDTO> update(@RequestBody TrainRoutineDTO trainRoutineDTO, @PathVariable("id") Long id){
        TrainRoutineDTO result = service.update(trainRoutineDTO, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<TrainRoutineListDTO>> list(@PathVariable("page") int page, 
        @PathVariable("size") int size,
        @RequestParam(name = "sort", required = false) String sort){
        List<TrainRoutineListDTO> result  = service.list(page, size, sort);
        return ResponseEntity.ok().body(result);        
    }

     /* ================ COUNT ================ */
     @GetMapping("/count")
     public ResponseEntity<Long> count(){
         long result = service.count();
         return ResponseEntity.ok().body(result);        
     }

}

