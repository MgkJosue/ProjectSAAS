package com.personaltrain.personaltrain.services.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personaltrain.personaltrain.dto.NewUserDTO;
import com.personaltrain.personaltrain.dto.UserDTO;
import com.personaltrain.personaltrain.exceptions.ResourceNotFoundException;
import com.personaltrain.personaltrain.models.User;
import com.personaltrain.personaltrain.repositories.UserRepository;
import com.personaltrain.personaltrain.services.UserService;

@Service
public class UserServiceImpl implements UserService{
    final ModelMapper modelMapper;
    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Autowired UserRepository repository, ModelMapper mapper){
        this.userRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public UserDTO create(NewUserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);        
        return modelMapper.map(user,  UserDTO.class); 
    }
    


    @Override
    @Transactional(readOnly = true)
    public UserDTO retrieve(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        
        user.setId(id);
        user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);       

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));        
        userRepository.deleteById(user.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> list() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList());        
    }
}
