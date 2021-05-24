package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.CategoryDAO;
import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.model.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(rollbackFor = Exception.class)
public class CategoryDAOImpl extends BaseDAOImpl<CategoryEntity> implements CategoryDAO<CategoryEntity> {

    @Override
    public int saveCategory(CategoryEntity category) {
        StringBuilder sql = new StringBuilder();
        sql.append("call category_create(?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), category.getName(),category.getCode(),
                category.getDescription(),category.getActiveFlag(),
                category.getCreatedDate(), category.getUpdatedDate());
    }

    @Override
    public void updateCategory(CategoryEntity category) {
        StringBuilder sql = new StringBuilder();
        sql.append("call category_update(?, ?, ?, ?, ?)");
        update(sql.toString(),category.getName(), category.getCode(), category.getDescription(),
                category.getActiveFlag(), category.getUpdatedDate());
    }

    @Override
    public List<CategoryEntity> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("call category_finALl()");
        return query(sql.toString(), new CategoryMapper());
    }

    @Override
    public CategoryEntity findById(int id) {
        String sql = "call category_findCategoryById(?)";
        List<CategoryEntity> category = query(sql, new CategoryMapper(), id);
        return category.isEmpty() ? null : category.get(0);
    }

    @Override
    public CategoryEntity findByCode(String code) {
        String sql = "call category_findCategoryByCode(?)";
        List<CategoryEntity> category = query(sql, new CategoryMapper(), code);
        return category.isEmpty() ? null : category.get(0);
    }

    @Override
    public int checkProductInCategory(int id) {
        String sql = "call category_checkProductInCategory(?)";
        return queryId(sql, new CategoryMapper(), id);
    }
}
