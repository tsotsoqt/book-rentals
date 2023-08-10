package com.example.bookrentals.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    Optional <UserEntity> findByEmail (String Email);
    Optional <UserEntity> findById (Long Id);


}