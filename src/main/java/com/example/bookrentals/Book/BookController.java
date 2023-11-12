package com.example.bookrentals.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    //return the book with the given isbn
    @GetMapping("/{ISBN}")
    public ResponseEntity<BookDTO> getBookByISBN(@PathVariable String ISBN) {
        BookDTO bookDto = bookService.getBookByISBN(ISBN);
        if (bookDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    //returns all books from a given author
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable String author) {
        List<BookDTO> bookDTOS = bookService.getBooksByAuthor(author);
        if (bookDTOS == null || bookDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }

    //check books that costs lower than given price
    @GetMapping("/price")
    public ResponseEntity<List<BookDTO>> getBooksByPrice(@RequestParam("price") double price){
        List<BookDTO> bookDTOS = bookService.getBooksByPrice(price);
        if (bookDTOS == null || bookDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }


    //change the stock from a book
    @PatchMapping("restock/{ISBN}")
    public ResponseEntity<BookDTO> updateStock(@PathVariable String ISBN, @RequestBody Map<String, Integer> stock){
        BookDTO updatedBookDto = bookService.updateStock(ISBN, stock.get("stock"));

        return new ResponseEntity<>(updatedBookDto,HttpStatus.OK);
    }

    //add a new book to the database
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDto) {
        bookDto = bookService.createBook(bookDto);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    //completely delete a book complete from database
    @DeleteMapping("/delete/{ISBN}")
    public ResponseEntity<Void> deleteBookByIsbn(@PathVariable String ISBN) {
        bookService.deleteBookByIsbn(ISBN);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //change the price in case of sales

    @PatchMapping("/{ISBN}/price")
    public ResponseEntity<BookDTO> updateBookPrice(@PathVariable String ISBN, @RequestBody Map<String, Double> price) {
        BookDTO updatedBookDto = bookService.updateBookPrice(ISBN, price.get("price"));
        if (updatedBookDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedBookDto, HttpStatus.OK);
    }

}