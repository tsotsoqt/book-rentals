package com.example.bookrentals.User;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity toEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setOnoma(userDTO.getOnoma());
        userEntity.setEpitheto(userDTO.getEpitheto());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPassword(userDTO.getPassword());


        return userEntity;
    }

    public UserDTO toDto(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setOnoma(userEntity.getOnoma());
        userDTO.setEpitheto(userEntity.getEpitheto());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setPassword(userEntity.getPassword());


        return userDTO;
    }
}
