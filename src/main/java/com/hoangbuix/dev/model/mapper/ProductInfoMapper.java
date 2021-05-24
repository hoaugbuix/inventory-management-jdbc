package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.ProductInfoEntity;

import java.sql.ResultSet;

public class ProductInfoMapper implements RowMapper<ProductInfoEntity> {
    @Override
    public ProductInfoEntity mapRow(ResultSet resultSet) {
        try {
            ProductInfoEntity product = new ProductInfoEntity();
            return product;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
