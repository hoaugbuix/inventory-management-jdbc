package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.ProductDAO;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.exception.InternalServerException;
import com.hoangbuix.dev.exception.NotFoundException;
import com.hoangbuix.dev.model.request.create.CreateProductInfoReq;
import com.hoangbuix.dev.model.request.update.UpdateProductInfoReq;
import com.hoangbuix.dev.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductDAO<ProductInfoEntity> productDAO;

    @Override
    public ProductInfoEntity save(CreateProductInfoReq req) {
        ProductInfoEntity product = new ProductInfoEntity();
        Object obj = productDAO.findProductInfoByCode(req.getCode());
        if (obj == null) {
            product.setCode(req.getCode());
            product.setName(req.getName());
            product.setDescription(req.getDescription());
            product.setImgUrl(req.getImgUrl());
            product.setCateId(req.getCateId());
            product.setActiveFlag(1);
            product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        } else {
            throw new DuplicateRecordException("dup roi nha");
        }
        int id = productDAO.saveProductInfo(product);
        return productDAO.findProductInfoById(id);
    }

    @Override
    public void update(int id, UpdateProductInfoReq req) {
        ProductInfoEntity product = productDAO.findProductInfoById(id);
        if (product == null) {
            throw new NotFoundException("");
        }
        try {
            product.setActiveFlag(1);
            product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            productDAO.updateProductInfo(product);
        } catch (Exception e) {
            throw new InternalServerException("xxx");
        }
    }

    @Override
    public void delete(int id) {
        ProductInfoEntity product = productDAO.findProductInfoById(id);
        if (product == null) {
            throw new NotFoundException("");
        }
        try {
            product.setActiveFlag(0);
            product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            productDAO.updateProductInfo(product);
        } catch (Exception e) {
            throw new InternalServerException("");
        }
    }

    @Override
    public List<ProductInfoEntity> findAll() {
        return productDAO.findProductInfoAll();
    }

    @Override
    public ProductInfoEntity findByCode(String code) {
        return productDAO.findProductInfoByCode(code);
    }

    @Override
    public ProductInfoEntity findById(int id) {
        return productDAO.findProductInfoById(id);
    }
}
