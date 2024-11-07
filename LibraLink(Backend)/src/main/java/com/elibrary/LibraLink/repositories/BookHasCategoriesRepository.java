package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.entities.books_has_categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookHasCategoriesRepository extends JpaRepository<books_has_categories,Integer> {
    List<books_has_categories> findByCategory_id(Integer categoryId);
}
