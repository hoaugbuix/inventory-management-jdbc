package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.CategoryDAO;
import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO<CategoryEntity> categoryDAO;

    @Override
    public CategoryEntity save(CategoryEntity cate) {
//        CategoryEntity newCate = categoryDAO.findByCode(cate.getCode());
        cate.setActiveFlag(1);
        cate.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        cate.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        int id = categoryDAO.save(cate);
        System.out.println(id);
        return categoryDAO.findById(id);
    }

    @Override
    public List<CategoryEntity> findByCode(String code) {
        return categoryDAO.findByCode(code);
    }
}
