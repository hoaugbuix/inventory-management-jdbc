package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.CategoryEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryEntity save(CategoryEntity cate);
}
