package com.example.bookrentals.Rental;


import com.example.bookrentals.Book.BookEntity;
import com.example.bookrentals.User.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rentals")


public class RentalEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")

    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "book_id")

    private BookEntity bookEntity;
    @Column(name = "rental_date")
    private LocalDate rentalDate;
    @Column(name = "return_date")
    private LocalDate returnDate;




}