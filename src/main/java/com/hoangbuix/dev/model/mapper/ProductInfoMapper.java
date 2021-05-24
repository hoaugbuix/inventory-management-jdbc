package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.ProductInfoEntity;

import java.sql.ResultSet;

public class ProductInfoMapper implements RowMapper<ProductInfoEntity> {
    @Override
    public ProductInfoEntity mapRow(ResultSet resultSet) {
        try {
            ProductInfoEntity product = new ProductInfoEntity();
            product.setId(resultSet.getInt("id"));
            product.setCode(resultSet.getString("code"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setImgUrl(resultSet.getString("img_url"));
            product.setActiveFlag(resultSet.getInt("active_flag"));
            product.setCreatedDate(resultSet.getDate("created_date"));
            product.setUpdatedDate(resultSet.getDate("updated_date"));
            product.setCateId(resultSet.getInt("cate_id"));
            return product;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
