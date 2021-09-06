package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.model.request.create.CreateCategoryReq;
import com.hoangbuix.dev.model.request.update.UpdateCategoryReq;
import com.hoangbuix.dev.service.CategoryService;
import com.hoangbuix.dev.validate.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryValidator categoryValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        if (binder.getTarget() == null) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        if (binder.getTarget().getClass() == CategoryEntity.class) {
            binder.setValidator(categoryValidator);
        }
    }

    @PostMapping("/category/save")
    private ResponseEntity<?> saveCategory(@Valid @RequestBody CreateCategoryReq req){
        CategoryEntity results = categoryService.save(req);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/category/edit/{id}")
    private ResponseEntity<?> editCategory(@Valid @RequestBody UpdateCategoryReq req, @PathVariable int id){
        categoryService.update(id, req);
        return new ResponseEntity<>("update success", HttpStatus.OK);
    }

    @DeleteMapping("/category/delete/{id}")
    private ResponseEntity<?> deleteCategory(@PathVariable int id){
        categoryService.delete(id);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @GetMapping("/category/view/{id}")
    private ResponseEntity<?> findCategoryById(@PathVariable int id){
        CategoryEntity category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/category/view-code/{code}")
    private ResponseEntity<?> findCategoryByCode(@PathVariable String code){
        CategoryEntity category = categoryService.findByCode(code);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
