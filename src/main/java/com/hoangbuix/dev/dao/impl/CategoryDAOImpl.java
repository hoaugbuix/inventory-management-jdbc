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
    public int save(CategoryEntity category) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `category`(`name`,`code`,`description`,`active_flag`,`created_date`,`updated_date`)");
        sql.append("VALUES (?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), category.getName(),category.getCode(),
                category.getDescription(),category.getActiveFlag(),
                category.getCreatedDate(), category.getUpdatedDate());
    }

    @Override
    public CategoryEntity findById(int id) {
        String sql = "select * from category where id = ?";
        List<CategoryEntity> category = query(sql, new CategoryMapper(), id);
        return category.isEmpty() ? null : category.get(0);
    }

    @Override
    public List<CategoryEntity> findByCode(String code) {
        String sql = "select * from category where code = ?";
        List<CategoryEntity> category = query(sql, new CategoryMapper(), code);
        return category.isEmpty() ? null : category;
    }
}
