package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.model.request.create.CreateCategoryReq;
import com.hoangbuix.dev.model.request.update.UpdateCategoryReq;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryEntity save(CreateCategoryReq req);
    void update(int id, UpdateCategoryReq req);
    void delete(int id);
    CategoryEntity findByCode(String code);
    CategoryEntity findById(int id);
}
