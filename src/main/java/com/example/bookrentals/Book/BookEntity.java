package com.example.bookrentals.Book;


import com.example.bookrentals.User.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")


public class BookEntity {
    @ManyToOne
    private UserEntity userEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    @Column(nullable = false, unique = true)
    private String ISBN;
    @Column(name="pagecount")
    private int pageCount;
    private double price;
    private int stock;




}
