package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.entity.ProductInStockEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductInStockService {
    ProductInStockEntity saveOrUpdate(InvoiceEntity req);
    List<ProductInStockEntity> findAll();
}
