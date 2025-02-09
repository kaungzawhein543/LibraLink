package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.Book;
import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    // CONSTANT VALUES
    private final CategoryRepository categoryRepository;

    // CONSTRUCTOR
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    // CREATE CATEGORY
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    // GET CATEGORY BY ID
    public Optional<Category> findCategoryById(Integer id){
        return categoryRepository.findById(id);
    }

    // GET ALL CATEGORIES
    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    // UPDATE Category By id
    public Category updateCategory(Category category){
        Optional<Category> originalCategory = categoryRepository.findById(category.getId());

        if(originalCategory.isPresent()){
            Category categoryForUpdate = originalCategory.get();
            categoryForUpdate.setName(category.getName());
            categoryForUpdate.setStatus(originalCategory.get().isStatus());

            return categoryRepository.save(categoryForUpdate);
        }else {
            throw new Error("Category Not Found With Id "+category.getId());
        }
    }

    // DELETE CATEGORY (SOFT)
    public void softDeleteCategory(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category categoryForDle = category.get();
            categoryForDle.setStatus(false);

            categoryRepository.save(categoryForDle);
        }else{
            throw new Error("Category Not Found With Id "+id);
        }
    }

    // DELETE  CATEGORY (HARD)
    public void permanentDeleteCategory(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.deleteById(id);
        }else{
            throw new Error("Category Not Found With Id "+id);
        }
    }
}
