package com.hoangbuix.dev.model.mapper;

import com.hoangbuix.dev.entity.HistoryEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryMapper implements RowMapper<HistoryEntity> {
    @Override
    public HistoryEntity mapRow(ResultSet resultSet) {
        try {
            HistoryEntity history = new HistoryEntity();
            history.setId(resultSet.getInt("id"));
            history.setActionName(resultSet.getString("action_name"));
            history.setType(resultSet.getInt("type"));
            try {
                ProductInfoEntity productInfo = new ProductInfoEntity();
                history.setId(resultSet.getInt("id"));
                history.setProductInfo(productInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            history.setQty(resultSet.getInt("qty"));
            history.setPrice(resultSet.getBigDecimal("price"));
            history.setActiveFlag(resultSet.getInt("active_flag"));
            history.setCreatedDate(resultSet.getDate("created_date"));
            history.setUpdatedDate(resultSet.getDate("updated_date"));
            return history;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
