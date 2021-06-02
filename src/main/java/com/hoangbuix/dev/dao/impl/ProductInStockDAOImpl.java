package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.ProductInStockDAO;
import com.hoangbuix.dev.entity.ProductInStockEntity;
import com.hoangbuix.dev.model.mapper.ProductInStockMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductInStockDAOImpl extends BaseDAOImpl<ProductInStockEntity> implements ProductInStockDAO<ProductInStockEntity> {

    @Override
    public int save(ProductInStockEntity productInStock) {
        String sql = "call productInStock_create(?, ?, ?, ?, ?, ?)";
        return insert(sql, productInStock.getProductInfos().getCateId(),
                productInStock.getQty(), productInStock.getPrice(), productInStock.getActiveFlag(),
                productInStock.getCreatedDate(), productInStock.getUpdatedDate());
    }

    @Override
    public void update(ProductInStockEntity instance) {

    }

    @Override
    public List<ProductInStockEntity> findAll() {
        String sql = "call productInStock_findAll()";
        return query(sql, new ProductInStockMapper());
    }

    @Override
    public ProductInStockEntity findById(int id) {
        return null;
    }

    @Override
    public ProductInStockEntity findByCode(String code) {
        return null;
    }

    @Override
    public ProductInStockEntity exist() {
        return null;
    }
}
