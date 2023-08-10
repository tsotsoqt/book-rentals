package com.example.bookrentals.Book;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String ISBN;
    private int pageCount;
    private double price;
    private int stock;



}
