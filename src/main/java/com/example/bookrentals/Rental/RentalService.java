package com.example.bookrentals.Rental;

import com.example.bookrentals.Book.BookEntity;
import com.example.bookrentals.Book.BookRepository;
import com.example.bookrentals.User.UserEntity;
import com.example.bookrentals.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RentalConverter rentalConverter;

    public class InsufficientStockException extends RuntimeException {
        public InsufficientStockException(String message) {
            super(message);
        }
    }

    //method to rent a book. sets the stock -1
    public void rentBook(Long userid, Long bookid) {
        UserEntity userEntity = userRepository.findById(userid).get();
        BookEntity bookEntity = bookRepository.findById(bookid).get();

        if (bookEntity.getStock() == 0) {
            throw new InsufficientStockException("Not enough stock to fulfill the rental.");
        }

        // Create a new rental entry
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setUserEntity(userEntity);
        rentalEntity.setBookEntity(bookEntity);
        rentalEntity.setRentalDate(LocalDate.now());
        rentalEntity.setReturnDate(LocalDate.now().plusDays(5));
        // Update the stock of the book
        bookEntity.setStock(bookEntity.getStock() - 1);

        // Save the rental and update the book in the database
        rentalRepository.save(rentalEntity);
        bookRepository.save(bookEntity);
    }

    //returns all the rentals done for the given book title
    public List<RentalDTO> getRentalsForBook(String bookName) {
        List<RentalEntity> rentalEntities = rentalRepository.findByBookEntityTitle(bookName);
        List<RentalDTO> rentalDTOS = rentalEntities.stream()
                .map(rentalConverter::toDTO)
                .collect(Collectors.toList());
        return rentalDTOS;
    }

    //returns all the rentals a user has done
    public List<RentalDTO> getRentalsForUser(String userName) {
        List<RentalEntity> rentalEntities = rentalRepository.findByUserEntityOnoma(userName);
        List<RentalDTO> rentalDTOS = rentalEntities.stream()
                .map(rentalConverter::toDTO)
                .collect(Collectors.toList());
        return rentalDTOS;
    }

    //method for the end of rental. it updates the stock back
    public void returnBook(Long rentalId) {
        RentalEntity rentalEntity = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental with ID " + rentalId + " not found"));

        // Set the return date to the current date
        rentalEntity.setReturnDate(LocalDate.now());

        // Save the updated rental in the database
        rentalRepository.save(rentalEntity);

        // Increase the stock count of the book associated with the rental
        BookEntity bookEntity = rentalEntity.getBookEntity();
        bookEntity.setStock(bookEntity.getStock() + 1);
        bookRepository.save(bookEntity);

    }

}
