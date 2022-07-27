package com.personaltrain.personaltrain.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class NewUserDTO {
    @NotNull(message = "User name exercise can't be null")
    private String name;
    private String nickName;
    private String email;
    private LocalDate registerDay;
    private Boolean loginStatus;
    private String password;
    private String userRol;
    private String description;   
}
