package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.service.CategoryService;
import com.hoangbuix.dev.validate.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    private ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryEntity cate){
        CategoryEntity results = categoryService.save(cate);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
