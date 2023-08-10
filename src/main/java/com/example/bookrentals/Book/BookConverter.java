package com.example.bookrentals.Book;


import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    public BookEntity toEntity(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBN(bookDTO.getISBN());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setPrice(bookDTO.getPrice());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setPageCount(bookDTO.getPageCount());
        bookEntity.setStock((bookDTO.getStock()));

        return bookEntity;
    }

    public BookDTO toDto(BookEntity bookEntity){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setISBN(bookEntity.getISBN());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setPrice(bookEntity.getPrice());
        bookDTO.setPageCount(bookEntity.getPageCount());
        bookDTO.setStock(bookEntity.getStock());
        return bookDTO;
    }
}
