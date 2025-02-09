package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.dtos.BookCategoryDTO;
import com.elibrary.LibraLink.entities.Book;
import com.elibrary.LibraLink.entities.BooksHasCategories;
import com.elibrary.LibraLink.repositories.BookHasCategoriesRepository;
import com.elibrary.LibraLink.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    // CONSTANT VALUES
    private final BookRepository bookRepository;
    private final BookHasCategoriesRepository bookHasCategoriesRepository;
    private final FileManagingService fileManagingService;


    public BookService(BookRepository bookRepository, BookHasCategoriesRepository bookHasCategoriesRepository, FileManagingService fileManagingService){
        this.bookRepository = bookRepository;
        this.bookHasCategoriesRepository = bookHasCategoriesRepository;
        this.fileManagingService = fileManagingService;
    }

    // CREATE BOOK
    public Book addBook(Book book, MultipartFile file,String userId) throws Exception {

        // GENERATE UUID FOR SAVING FILE AND BOOK
        UUID book_UUID = UUID.randomUUID();

        // STORE BOOK FILE IN SERVER PATH
        if(file.isEmpty()) {
            throw new IllegalArgumentException("File is not valid");
        }
        String filePath = fileManagingService.saveFileTOServer(file,userId,book_UUID);

        // SET BOOK DATA
        book.setId(book_UUID);
        book.setBook_path(filePath);
        book.setCreated_at(LocalDateTime.now());

        return bookRepository.save(book);
    }

    // GET BOOK BY ID
    public Optional<Book> findBookById(UUID id){
        return bookRepository.findById(id);
    }

    // GET ALL BOOKS
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // UPDATE BOOK BY ID
    public Book updateBook(Book updatedBook){
        Optional<Book> bookBeforeUpdate = bookRepository.findById(updatedBook.getId());

        if(bookBeforeUpdate.isPresent()){
            Book bookForUpdate = bookBeforeUpdate.get();
            bookForUpdate.setName(updatedBook.getName());
            bookForUpdate.setBook_path(updatedBook.getBook_path());
            bookForUpdate.setPages(updatedBook.getPages());
            bookForUpdate.setPreview_photo_path(updatedBook.getPreview_photo_path());
            bookForUpdate.setSocial_share_count(updatedBook.getSocial_share_count());

            return  bookRepository.save(bookForUpdate);
        }else{
            throw new RuntimeException("Book Not Found with id "+updatedBook.getId());
        }
    }

    // DELETE BOOK (SOFT)
    public void softDeleteBook(UUID id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            Book bookForDle = book.get();
            bookForDle.setStatus(false);

            bookRepository.save(bookForDle);
        }else{
            throw new Error("Book Not Found With Id "+id);
        }
    }

    // DELETE BOOK (HARD)
    public void permanentDeleteBook(UUID id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            bookRepository.deleteById(id);
        }else{
            throw new Error("Book Not Found With Id "+id);
        }
    }

    // FIND BOOKS BY CATEGORY
    public List<Book> findBooksByCategory(Integer id){
        List<BooksHasCategories> bookIdsAndCategoryIDs = bookHasCategoriesRepository.findByCategory_id(id);
        if(!bookIdsAndCategoryIDs.isEmpty()){
            List<BookCategoryDTO> booksByCategoryDTOS = bookRepository.findBookByCategory(id);

            List<Book> booksByCategory = booksByCategoryDTOS.stream()
                    .map(dto ->{
                        Book book = new Book();
                        book.setId(dto.getBookId());
                        book.setName(dto.getBookName());
                        book.setPages(dto.getPages());
                        book.setStatus(dto.isStatus());
                        book.setPreview_photo_path(dto.getPreview_photo_path());
                        book.setBook_path(dto.getBook_path());
                        book.setSocial_share_count(dto.getSocial_share_count());
                        book.setCreated_at(dto.getCreated_at());
                        return book;
                    })
                    .collect(Collectors.toList());

            return booksByCategory;
        }
        return new ArrayList<>();
    }
}
