package saas.personaltrain.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_LEARNIG")
@Getter
@Setter

public class Learning {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "INFO_ROUTINE")    
    private String infoRoutine;
    @Column(name = "DESCRIPTION")    
    private String description;

    @OneToOne(mappedBy = "learning")
    private TrainRoutine trainRoutine;
  
}
