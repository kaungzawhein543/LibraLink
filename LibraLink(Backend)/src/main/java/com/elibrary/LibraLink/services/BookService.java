package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.Book;
import com.elibrary.LibraLink.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //Create Book
    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    //Get Book By ID
    public Optional<Book> findBookById(UUID id){
        return bookRepository.findById(id);
    }

    //Get All Books
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //Update Book By Id
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
}
