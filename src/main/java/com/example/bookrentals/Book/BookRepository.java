package com.example.bookrentals.Book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findByISBN(String ISBN);

    Optional<BookEntity> findById(Long id);
    @Query("SELECT b FROM BookEntity b WHERE b.price < :price")
    List<BookEntity> findByPrice(double price);
    @Query("SELECT b FROM BookEntity b WHERE b.author = :author")
    List<BookEntity> findByAuthor(String author);
    @Query("DELETE FROM BookEntity b WHERE b.ISBN = :ISBN")
    void deleteByISBN(@Param("ISBN") String ISBN);

}