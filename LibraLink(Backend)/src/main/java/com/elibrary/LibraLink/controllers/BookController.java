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

    @PostMapping("add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatusCode.valueOf(200));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        return new ResponseEntity<>(bookService.findBookById(id).get(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatusCode.valueOf(200));
    }
}
