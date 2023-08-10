package com.example.bookrentals.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;


    //adds a user to the database
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            // You can throw an exception, return an error response, or handle the duplicate registration in some way.
            // For now, let's just return null to indicate a duplicate registration.
            return null;
        }
        UserEntity userEntity = userConverter.toEntity(userDTO);
        userRepository.save(userEntity);
        userDTO = userConverter.toDto(userEntity);

        return userDTO;
    }

    // it just verifies if the emails with password are correct.
    // its like a "fake login"
    public boolean loginUser(UserDTO userDTO) {
        // Check if the email is registered in the database
        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail()).get();

        if (userEntity != null && userEntity.getPassword().equals(userDTO.getPassword())) {
            // Email is registered and password is correct
            return true;
        } else {
            // Invalid email or password
            return false;
        }
    }

}
