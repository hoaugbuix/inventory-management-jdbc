package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryEntity save(CategoryEntity cate);
    List<CategoryEntity> findByCode(String code);
}
