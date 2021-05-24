package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.model.request.CreateProductInfoReq;
import com.hoangbuix.dev.model.request.UpdateProductInfoReq;
import org.springframework.stereotype.Service;

@Service
public interface ProductInfoService {
    ProductInfoEntity save(CreateProductInfoReq req);
    void update(int id, UpdateProductInfoReq req);
    void delete(int id);
    ProductInfoEntity findByCode(String code);
    ProductInfoEntity findById(int id);
}
