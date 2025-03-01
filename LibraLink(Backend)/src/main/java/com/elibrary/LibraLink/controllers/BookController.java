package com.elibrary.LibraLink.controllers;


import com.elibrary.LibraLink.dtos.BookDTO;
import com.elibrary.LibraLink.dtos.CategoryDTO;
import com.elibrary.LibraLink.entities.Book;
import com.elibrary.LibraLink.entities.User;
import com.elibrary.LibraLink.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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

    // CONSTANT VALUES
    private final BookService bookService;
    private final ModelMapper modelMapper;

    // CONSTRUCTORS
    public BookController(BookService bookService, ModelMapper modelMapper){
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    // ADD BOOK
    @PostMapping(value="add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Book> addBook(@RequestParam("file") MultipartFile file, @RequestParam("bookDTOJson") String bookDTOJson,@RequestParam("userId") String userId) throws Exception {

        ObjectMapper objectmapper = new ObjectMapper();
        BookDTO bookDTO = objectmapper.readValue(bookDTOJson, BookDTO.class);

        // Map DTO to Book entity
        Book book = modelMapper.map(bookDTO,Book.class);
        return new ResponseEntity<>(bookService.addBook(book,file,userId), HttpStatusCode.valueOf(200));
    }

    // GET BOOK BY ID
    @GetMapping("get/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable UUID id) {
        return bookService.findBookById(id)
                .map(book -> modelMapper.map(book , BookDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // GET ALL BOOKS
    @GetMapping("getAll")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO>  bookDTOS = bookService.getAllBooks()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return  ResponseEntity.ok(bookDTOS);
    }

    // UPDATE CATEGORY(SOFT)
    @PutMapping(value="update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(book),HttpStatusCode.valueOf(200));
    }

    // GET BOOK BY CATEGORY
    @GetMapping("category/{id}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.findBooksByCategory(id));
    }

    // DELETE BOOK(SOFT)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        bookService.softDeleteBook(id);
        return ResponseEntity.ok("Delete Book Successfully.");
    }
}
