package com.personaltrain.personaltrain.models;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
/*import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;*/
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_LEARNIG")
@Getter
@Setter


public class Learning {
    //Atributos de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "INFO_ROUTINE")    
    private String infoRoutine;
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


    
   /*  @ManyToOne
    @JoinColumn(name="TRAIN_ROUTINE_ID", nullable=false)
    private TrainRoutine trainRoutine; */   
}
