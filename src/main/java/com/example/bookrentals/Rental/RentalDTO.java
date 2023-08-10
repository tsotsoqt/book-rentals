package com.example.bookrentals.Rental;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter

public class RentalDTO {
    private Long id;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private Long userid;
    private Long bookid;

}