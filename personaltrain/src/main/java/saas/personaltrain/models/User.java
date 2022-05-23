package saas.personaltrain.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_USER")
@Getter
@Setter

public class User {
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
    private Date registerDay;

    @Column(name = "LOGIN_STATUS")
    private String timeBegin;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_ROL")
    private String userRol;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "User")
    private List<TrainRoutine> trainRoutine;
}