package com.personaltrain.personaltrain.models;


import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_TRAIN_ROUTINE")
@Getter
@Setter

public class TrainRoutine {
    //Atributos de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME_ROUTINE")
    private String nameRoutine;

    @Column(name = "TIME_BEGIN")
    private short timeBegin;

    @Column(name = "TIME_END")
    private short timeEnd;

    @Column(name = "NUMBER_OF_EXERCISES")    
    private short numberOfExercises;

    @Column(name = "DESCRIPTION")
    private String description;



    //Parte de auditoria 
    @Column(name = "CREATED_DATE")    
    private Calendar createdDate;
    @Column(name = "CREATED_BY")    
    private String createdBy;  

    @Column(name = "UPDATED_DATE")    
    private Calendar updatedDate;
    @Column(name = "UPDATED_BY")    
    private String updatedBy;  

    @PrePersist
    public void prePersist(){
        createdDate = Calendar.getInstance();
        createdBy = "user1";
    }

    @PreUpdate
    public void preUpdate(){
        updatedDate = Calendar.getInstance();
        updatedBy = "user2";
    }
     
    @OneToMany(mappedBy = "trainRoutine")
    private List<Exercise> exercises;


    /* 
    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;
    */
    
}
