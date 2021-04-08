package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.CategoryDAO;
import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO<CategoryEntity> categoryDAO;

    @Override
    public CategoryEntity save(CategoryEntity cate) {
        cate.setActiveFlag(1);
        cate.setCreatedDate(new Date());
        cate.setUpdatedDate(new Date());
        int id = categoryDAO.save(cate);
        return categoryDAO.findById(id);
    }
}
