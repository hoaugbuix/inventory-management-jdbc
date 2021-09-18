package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.HistoryDAO;
import com.hoangbuix.dev.entity.HistoryEntity;
import com.hoangbuix.dev.model.mapper.HistoryMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class HistoryDAOImpl extends BaseDAOImpl<HistoryEntity> implements HistoryDAO<HistoryEntity> {
    @Override
    public int save(HistoryEntity history) {
        log.info("save history = " + history);
        String sql = "call history_create(?,?,?,?,?,?,?,?)";
        return insert(sql, history.getActionName(), history.getType(), history.getProductInfo().getId(),
                history.getQty(), history.getPrice(), history.getActiveFlag(), history.getCreatedDate(),
                history.getUpdatedDate());
    }

    @Override
    public List<HistoryEntity> findAll() {
        String sql = "call history_findAll()";
        return query(sql, new HistoryMapper());
    }

    @Override
    public HistoryEntity findById(int id) {
        String sql = "call history_findById(?)";
        List<HistoryEntity> history = query(sql, new HistoryMapper(), id);
        return history.isEmpty() ? null : history.get(0);
    }
}
