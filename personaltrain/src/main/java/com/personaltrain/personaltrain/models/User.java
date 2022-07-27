package com.personaltrain.personaltrain.models;

import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_USER")
@Getter
@Setter

public class User {

    //Atributos de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "REGISTER_DAY")    
    private LocalDate registerDay;

    @Column(name = "LOGIN_STATUS")
    private Boolean loginStarus;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_ROL")
    private String userRol;

    @Column(name = "DESCRIPTION")
    private String description;



    //Esto es la auditoria
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



/* 
    @OneToMany(mappedBy = "user")
    private List<TrainRoutine> trainRoutine;
*/   
}
