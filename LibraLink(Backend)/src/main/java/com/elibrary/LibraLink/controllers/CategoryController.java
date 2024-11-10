package com.elibrary.LibraLink.controllers;

import com.elibrary.LibraLink.entities.Book;
import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.services.BookService;
import com.elibrary.LibraLink.services.CategoryService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //ADD CATEGORY
    @PostMapping("add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatusCode.valueOf(200));
    }

    //GET CATEGORY BY ID
    @GetMapping("get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.findCategoryById(id).get(), HttpStatusCode.valueOf(200));
    }

    //GET ALL CATEGORIES
    @GetMapping("getAll")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatusCode.valueOf(200));
    }

    //UPDATE CATEGORY
    @PutMapping("update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategory(category),HttpStatusCode.valueOf(200));
    }

    //DELETE CATEGORY(SOFT)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
        categoryService.softDeleteCategory(id);
        return ResponseEntity.ok("Delete Category Successfully.");
    }
}
