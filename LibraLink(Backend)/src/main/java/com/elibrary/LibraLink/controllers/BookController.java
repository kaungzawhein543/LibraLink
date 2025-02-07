package com.elibrary.LibraLink.controllers;


import com.elibrary.LibraLink.dtos.BookDTO;
import com.elibrary.LibraLink.dtos.CategoryDTO;
import com.elibrary.LibraLink.entities.Book;
import com.elibrary.LibraLink.entities.User;
import com.elibrary.LibraLink.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper){
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    //ADD BOOK
    @PostMapping(value="add")
    public ResponseEntity<Book> addBook(@RequestParam("file") MultipartFile file, @ModelAttribute Book book,@RequestParam("userId") String userId) throws IOException {
        return new ResponseEntity<>(bookService.addBook(book,file,userId), HttpStatusCode.valueOf(200));
    }

    //GET BOOK BY ID
    @GetMapping("get/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String id) {
        return bookService.findBookById(id)
                .map(book -> modelMapper.map(book , BookDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //GET ALL BOOKS
    @GetMapping("getAll")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO>  bookDTOS = bookService.getAllBooks()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return  ResponseEntity.ok(bookDTOS);
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
    public ResponseEntity<String> deleteBook(@PathVariable String id){
        bookService.softDeleteBook(id);
        return ResponseEntity.ok("Delete Book Successfully.");
    }
}
