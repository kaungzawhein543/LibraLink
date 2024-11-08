package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.entities.BooksHasCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookHasCategoriesRepository extends JpaRepository<BooksHasCategories,Integer> {
    List<BooksHasCategories> findByCategory_id(Integer categoryId);
}
