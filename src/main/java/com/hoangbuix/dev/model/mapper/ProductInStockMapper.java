package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.ProductInStockEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInStockMapper implements RowMapper<ProductInStockEntity> {
    @Override
    public ProductInStockEntity mapRow(ResultSet resultSet) {
        try {
            ProductInStockEntity productInStock = new ProductInStockEntity();
            productInStock.setId(resultSet.getInt("id"));
            try {
                ProductInfoEntity product = new ProductInfoEntity();
                product.setId(resultSet.getInt("id"));
                product.setCode(resultSet.getString("code"));
//                product.setName(resultSet.getString("name"));
                productInStock.setProductInfos(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
            productInStock.setQty(resultSet.getInt("qty"));
            productInStock.setPrice(resultSet.getBigDecimal("price"));
            productInStock.setActiveFlag(resultSet.getInt("active_flag"));
            productInStock.setCreatedDate(resultSet.getDate("created_date"));
            productInStock.setUpdatedDate(resultSet.getDate("updated_date"));
            return productInStock;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
