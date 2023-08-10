package com.example.bookrentals.Book;


import com.example.bookrentals.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookConverter bookConverter;

    @Transactional

    //returns a book with a specific ISBN

    public BookDTO getBookByISBN(String ISBN) {
        Optional<BookEntity> bookEntity = Optional.ofNullable(bookRepository.findByISBN(ISBN));
        return bookEntity.map(bookConverter::toDto).orElse(null);

    }

    //returns books written by an author
    public List<BookDTO> getBooksByAuthor(String author) {
        List<BookEntity> bookEntities = bookRepository.findByAuthor(author);
        if (bookEntities == null || bookEntities.isEmpty()) {
            return null;
        }

        List<BookDTO> bookDtos = bookEntities.stream()
                .map(bookConverter::toDto)
                .collect(Collectors.toList());

        return bookDtos;
    }

    //returns books that costs less than the given price
    public List<BookDTO> getBooksByPrice(double price) {
        List<BookEntity> bookEntities = bookRepository.findByPrice(price);
        if (bookEntities == null || bookEntities.isEmpty()) {
            return null;
        }

        List<BookDTO> bookDtos = bookEntities.stream()
                .map(bookConverter::toDto)
                .collect(Collectors.toList());
        return bookDtos;
    }

    public class InsufficientStockException extends RuntimeException {
        public InsufficientStockException(String message) {
            super(message);
        }
    }
    @Transactional

    //adds a new book to the database

    public BookDTO createBook(BookDTO bookDto) {
        BookEntity bookEntity = bookConverter.toEntity(bookDto);
        bookRepository.save(bookEntity);
        bookDto = bookConverter.toDto(bookEntity);

        return bookDto;
    }
    @Transactional

    public void deleteBookByIsbn(String ISBN) {
        bookRepository.deleteByISBN(ISBN);
    }

    //changes the price in case of sales
    public BookDTO updateBookPrice(String ISBN, Double price) {
        BookEntity bookEntity = bookRepository.findByISBN(ISBN);
        if (bookEntity == null) {
            return null;
        }
        bookEntity.setPrice(price);
        bookRepository.save(bookEntity);
        return bookConverter.toDto(bookEntity);
    }

    //change stock if we add more to the library or some gets thrown away
    public BookDTO updateStock(String ISBN, int stock) {
        BookEntity bookEntity = bookRepository.findByISBN(ISBN);
        if (bookEntity == null) {
            return null;
        }
        bookEntity.setStock(stock);
        bookRepository.save(bookEntity);
        return bookConverter.toDto(bookEntity);

}
}