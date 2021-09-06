package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.model.request.create.CreateProductInfoReq;
import com.hoangbuix.dev.model.request.update.UpdateProductInfoReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductInfoService {
    ProductInfoEntity save(CreateProductInfoReq req);
    void update(int id, UpdateProductInfoReq req);
    void delete(int id);
    List<ProductInfoEntity> findAll();
    ProductInfoEntity findByCode(String code);
    ProductInfoEntity findById(int id);
}
