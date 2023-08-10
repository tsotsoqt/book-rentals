package com.example.bookrentals.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
    List<RentalEntity> findByBookEntityTitle(String bookName);
    List<RentalEntity> findByUserEntityOnoma(String userName);

}