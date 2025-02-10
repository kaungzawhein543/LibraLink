package com.elibrary.LibraLink.controllers;

import com.elibrary.LibraLink.dtos.CategoryDTO;
import com.elibrary.LibraLink.entities.Book;
import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.services.BookService;
import com.elibrary.LibraLink.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    // CONSTANT VALUES
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    // CONSTRUCTOR
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper){
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    // ADD CATEGORY
    @PostMapping("add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatusCode.valueOf(200));
    }

    // GET CATEGORY BY ID
    @GetMapping("get/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        return categoryService.findCategoryById(id)
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // GET ALL CATEGORIES
    @GetMapping("getAll")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOS = categoryService.findAllCategories()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryDTOS);
    }

    // UPDATE CATEGORY
    @PutMapping("update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategory(category),HttpStatusCode.valueOf(200));
    }

    // DELETE CATEGORY(SOFT)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
        categoryService.softDeleteCategory(id);
        return ResponseEntity.ok("Delete Category Successfully.");
    }
}
