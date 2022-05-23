package saas.personaltrain.models;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="TBL_TRAIN_ROUTINE")
@Getter
@Setter
public class TrainRoutine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME_ROUTINE")
    private String nameRoutine;

    @Column(name = "DATE_TRAIN")    
    private Date dateTrain;

    @Column(name = "TIME_BEGIN")
    private Time timeBegin;

    @Column(name = "TIME_END")
    private Time timeEnd;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "trainRoutine")
    private List<Exercise> exercises;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;
    
}
