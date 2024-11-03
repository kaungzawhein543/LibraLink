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

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    //Create Category
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    //Get Category By ID
    public Optional<Category> findCategoryById(Integer id){
        return categoryRepository.findById(id);
    }

    //Get All Categories
    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    //Update Category By id
    public Category updateCategory(Category category){
        Optional<Category> originalCategory = categoryRepository.findById(category.getId());

        if(originalCategory.isPresent()){
            Category categoryForUpdate = originalCategory.get();
            categoryForUpdate.setName(category.getName());

            return categoryRepository.save(categoryForUpdate);
        }else {
            throw new Error("Category Not Found With Id "+category.getId());
        }
    }

    //Delete Category (soft)
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

    //Delete Category (hard)
    public void permanentDeleteCategory(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.deleteById(id);
        }else{
            throw new Error("Category Not Found With Id "+id);
        }
    }
}
