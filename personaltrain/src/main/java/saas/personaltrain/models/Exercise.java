package saas.personaltrain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_EXERCISE")
@Getter
@Setter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NAME")    
    private String name;

    @Column(name = "MUSCULAR_GROUP")    
    private String muscularGroup;

    @Column(name = "DESCRIPTION")    
    private String description;

    @ManyToOne
    @JoinColumn(name="TRAIN_ROUTINE_ID", nullable=false)
    private TrainRoutine trainRoutine;
    
}
