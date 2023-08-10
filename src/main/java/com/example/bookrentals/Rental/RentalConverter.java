package com.example.bookrentals.Rental;


import com.example.bookrentals.Book.BookRepository;
import com.example.bookrentals.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalConverter {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public  RentalEntity toEntity(RentalDTO rentalDTO) {
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setId(rentalDTO.getId());
        rentalEntity.setRentalDate(rentalDTO.getRentalDate());
        rentalEntity.setReturnDate(rentalDTO.getReturnDate());
        rentalEntity.setUserEntity(userRepository.findById(rentalDTO.getUserid()).orElse(null));
        rentalEntity.setBookEntity(bookRepository.findById(rentalDTO.getBookid()).orElse(null));
        return rentalEntity;
    }

    public  RentalDTO toDTO(RentalEntity rentalEntity) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rentalEntity.getId());
        rentalDTO.setRentalDate(rentalEntity.getRentalDate());
        rentalDTO.setReturnDate(rentalEntity.getReturnDate());
        rentalDTO.setUserid(rentalEntity.getUserEntity().getId());
        rentalDTO.setBookid(rentalEntity.getBookEntity().getId());
        return rentalDTO;
    }
}