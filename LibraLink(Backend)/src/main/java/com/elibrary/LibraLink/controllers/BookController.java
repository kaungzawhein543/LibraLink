package com.elibrary.LibraLink.controllers;


import com.elibrary.LibraLink.entities.Book;
import com.elibrary.LibraLink.entities.User;
import com.elibrary.LibraLink.services.BookService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    //ADD BOOK
    @PostMapping("add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatusCode.valueOf(200));
    }

    //GET BOOK BY ID
    @GetMapping("get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        return new ResponseEntity<>(bookService.findBookById(id).get(), HttpStatusCode.valueOf(200));
    }

    //GET ALL BOOKS
    @GetMapping("getAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatusCode.valueOf(200));
    }

    //UPDATE CATEGORY(SOFT)
    @PutMapping("update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(book),HttpStatusCode.valueOf(200));
    }

    //GET BOOK BY CATEGORY
    @GetMapping("category/{id}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.findBooksByCategory(id));
    }

    //DELETE BOOK(SOFT)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        bookService.softDeleteBook(id);
        return ResponseEntity.ok("Delete Book Successfully.");
    }
}
