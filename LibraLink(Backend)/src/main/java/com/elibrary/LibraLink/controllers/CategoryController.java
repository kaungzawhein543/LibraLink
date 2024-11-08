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

    @PostMapping("add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatusCode.valueOf(200));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.findCategoryById(id).get(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatusCode.valueOf(200));
    }
}
