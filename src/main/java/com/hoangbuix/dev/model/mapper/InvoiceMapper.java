package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper implements RowMapper<InvoiceEntity> {
    @Override
    public InvoiceEntity mapRow(ResultSet resultSet) {
        try {
            InvoiceEntity invoice = new InvoiceEntity();
            invoice.setId(resultSet.getInt("id"));
            invoice.setCode(resultSet.getString("code"));
            invoice.setType(resultSet.getInt("type"));
//            try {
//                ProductInfoEntity productInfo = new ProductInfoEntity();
//                productInfo.setId(resultSet.getInt("id"));
//                productInfo.setCode(resultSet.getString("code"));
//                invoice.setProductInfos(productInfo);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
            invoice.setProductId(resultSet.getInt("product_id"));
            invoice.setQty(resultSet.getInt("qty"));
            invoice.setPrice(resultSet.getBigDecimal("price"));
            invoice.setToDate(resultSet.getDate("to_date"));
            invoice.setFromDate(resultSet.getDate("from_date"));
            invoice.setActiveFlag(resultSet.getInt("active_flag"));
            invoice.setCreatedDate(resultSet.getDate("created_date"));
            invoice.setUpdatedDate(resultSet.getDate("updated_date"));
            return invoice;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
