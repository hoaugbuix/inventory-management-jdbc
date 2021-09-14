package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.CategoryDAO;
import com.hoangbuix.dev.dao.ProductDAO;
import com.hoangbuix.dev.entity.CategoryEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.exception.InternalServerException;
import com.hoangbuix.dev.exception.NotFoundException;
import com.hoangbuix.dev.model.request.create.CreateProductInfoReq;
import com.hoangbuix.dev.model.request.update.UpdateProductInfoReq;
import com.hoangbuix.dev.service.ProductInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ProductInfoServiceImpl implements ProductInfoService {
    final static Logger log = Logger.getLogger(ProductInfoServiceImpl.class);
    @Autowired
    private ProductDAO<ProductInfoEntity> productDAO;

    @Autowired
    private CategoryDAO<CategoryEntity> categoryDAO;

    @Override
    public ProductInfoEntity save(CreateProductInfoReq req) {
        log.info("product info");
        ProductInfoEntity product = new ProductInfoEntity();
        Object obj = productDAO.findProductInfoByCode(req.getCode());
        CategoryEntity category = categoryDAO.findById(req.getCateId());
           if (obj == null) {
               product.setCode(req.getCode());
               product.setName(req.getName());
               product.setDescription(req.getDescription());
               product.setImgUrl(req.getImgUrl());
               if (category.getId() != null && category.getId() != 0){
                   product.setCateId(category.getId());
               }else  {
                   throw new NotFoundException("Không tìm thấy");
               }
               product.setActiveFlag(1);
               product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
               product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

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
        ProductInfoEntity productInfo = productDAO.findProductInfoByCode(code);
        if (productInfo.getCode() == null) {
            throw new NotFoundException("Không tìm thấy code của bạn!");
        }
        return productInfo;
    }

    @Override
    public ProductInfoEntity findById(int id) {
        ProductInfoEntity productInfo=  productDAO.findProductInfoById(id);
        if (productInfo.getCode() == null) {
            throw new NotFoundException("Không tìm thấy code của bạn!");
        }
        return productInfo;
    }
}
