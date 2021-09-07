package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.CategoryDAO;
import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.exception.BadRequestException;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.exception.InternalServerException;
import com.hoangbuix.dev.exception.NotFoundException;
import com.hoangbuix.dev.model.request.create.CreateCategoryReq;
import com.hoangbuix.dev.model.request.update.UpdateCategoryReq;
import com.hoangbuix.dev.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    private static final Logger log = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDAO<CategoryEntity> categoryDAO;

    @Override
    public CategoryEntity save(CreateCategoryReq req) {
        CategoryEntity cate = new CategoryEntity();
        Object obj = categoryDAO.findByCode(req.getCode());
        if (obj == null) {
            cate.setName(req.getName());
            cate.setCode(req.getCode());
            cate.setDescription(req.getDescription());
            cate.setActiveFlag(1);
            cate.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            cate.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        } else {
            throw new DuplicateRecordException("da ton tai");
        }

        int id = categoryDAO.saveCategory(cate);
        return categoryDAO.findById(id);
    }

    @Override
    public void update(int id, UpdateCategoryReq req) {
        log.info("Update category " + req.toString());
        CategoryEntity cate = categoryDAO.findById(id);
        if (cate != null) {
            throw new NotFoundException("Category khong ton tai");
        }
        try {
            cate.setName(req.getName());
            cate.setCode(req.getCode());
            cate.setDescription(req.getDescription());
            cate.setActiveFlag(req.getActiveFlag());
            cate.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            categoryDAO.updateCategory(cate);
        } catch (Exception e) {
            throw new InternalServerException("Lỗi khi chỉnh sửa category");
        }
    }

    @Override
    public void delete(int id) {
        CategoryEntity cate = categoryDAO.findById(id);
        if (cate == null) {
            throw new NotFoundException("Category khong ton tai");
        }
        // Check product in category
        int cateId = categoryDAO.checkProductInCategory(id);
        if (cateId == 1) {
            throw new BadRequestException("Có sản phẩm thuộc category không thể xóa");
        }
        try {
            cate.setActiveFlag(0);
            cate.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            categoryDAO.updateCategory(cate);
        } catch (Exception e) {
            throw new InternalServerException("Lỗi khi xóa category");
        }
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public CategoryEntity findByCode(String code) {
        return categoryDAO.findByCode(code);
    }

    @Override
    public CategoryEntity findById(int id) {
        return categoryDAO.findById(id);
    }
}
