package com.example.bookrentals.Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    //rent a book endpoint
    @PostMapping("/users/{userId}/books/{bookId}")
    public ResponseEntity<String> rentBook(
            @PathVariable Long userId,
            @PathVariable Long bookId
    ) {
        rentalService.rentBook(userId, bookId);
        return ResponseEntity.ok("Book rented successfully");
    }

    //rentals for a book endpoint

    @GetMapping("/books/{bookName}/rents")
    public ResponseEntity<List<RentalDTO>> getRentalsForBook(@PathVariable String bookName) {
        List<RentalDTO> rentals = rentalService.getRentalsForBook(bookName);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }
    //rentals for a user endpoint

    @GetMapping("/users/{userOnoma}/rents")
    public ResponseEntity<List<RentalDTO>> getRentalsForUser(@PathVariable String userOnoma) {
        List<RentalDTO> rentals = rentalService.getRentalsForUser(userOnoma);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    //return book endpoint

    @PutMapping("/{rentalId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long rentalId) {
        rentalService.returnBook(rentalId);
        return ResponseEntity.ok("Book returned successfully");
    }


}