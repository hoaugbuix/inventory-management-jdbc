package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/save")
    private ResponseEntity<?> saveCategory(CategoryEntity cate){
        CategoryEntity results = categoryService.save(cate);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
