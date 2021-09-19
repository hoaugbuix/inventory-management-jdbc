package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.ProductDAO;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.model.mapper.ProductInfoMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductDAOImpl extends BaseDAOImpl<ProductInfoEntity> implements ProductDAO<ProductInfoEntity> {

    @Override
    public int saveProductInfo(ProductInfoEntity product) {
        String sql = "call productInfo_create(?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, product.getCode(), product.getName(), product.getDescription(),
                product.getImgUrl(), product.getActiveFlag(), product.getCreatedDate(), product.getUpdatedDate(),
                product.getCategories().getId());
    }

    @Override
    public void updateProductInfo(ProductInfoEntity product) {
        String sql = "call productInfo_update(?,?,?,?,?,?,?)";
        update(sql, product.getCode(), product.getName(), product.getDescription(), product.getImgUrl(),
                product.getActiveFlag(), product.getUpdatedDate(), product.getCategories().getId());
    }

    @Override
    public List<ProductInfoEntity> findProductInfoAll() {
        String sql = "call productInfo_findProductInfoAll()";
        return query(sql, new ProductInfoMapper());
    }

    @Override
    public ProductInfoEntity findProductInfoById(int id) {
        String sql = "call productInfo_findProductInfoById(?)";
        List<ProductInfoEntity> product = query(sql, new ProductInfoMapper(), id);
        return product.isEmpty() ? null : product.get(0);
    }

    @Override
    public ProductInfoEntity findProductInfoByCode(String code) {
        String sql = "call productInfo_findProductInfoByCode(?)";
        List<ProductInfoEntity> product = query(sql, new ProductInfoMapper(), code);
        return product.isEmpty() ? null : product.get(0);
    }
}
