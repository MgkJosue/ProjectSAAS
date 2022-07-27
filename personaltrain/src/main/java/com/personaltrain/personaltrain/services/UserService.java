package com.personaltrain.personaltrain.services;

import java.util.List;

import com.personaltrain.personaltrain.dto.NewUserDTO;
import com.personaltrain.personaltrain.dto.UserDTO;

public interface UserService {
    public UserDTO create(NewUserDTO userDTO);
    public UserDTO retrieve(Long id);
    public UserDTO update(UserDTO userDTO, Long id);
    public void delete(Long id);
    public List<UserDTO> list();
}
