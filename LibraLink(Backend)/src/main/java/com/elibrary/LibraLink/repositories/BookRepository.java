package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.dtos.BookCategoryDTO;
import com.elibrary.LibraLink.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT new com.elibrary.LibraLink.dto.BookCategoryDTO(b.id,b.name,b.created_at,b.pages,b.preview_photo_path,b.book_path,b.social_share_count,"+
    "c.id,c.name) FROM Book b JOIN BookHasCategoryDTO bhc ON b.id = bhc.bookId"+
    "JOIN Category c ON bhc.category.id =c.id "+
    "WHERE c.id = :categoryId")
    List<BookCategoryDTO> findBookByCategory(@Param("categoryId") Integer categoryId);
}
