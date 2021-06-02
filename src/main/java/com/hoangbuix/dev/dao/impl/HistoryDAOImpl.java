package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.HistoryDAO;
import com.hoangbuix.dev.entity.HistoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class HistoryDAOImpl extends BaseDAOImpl<HistoryEntity> implements HistoryDAO<HistoryEntity> {
    @Override
    public int save(HistoryEntity instance) {
        return 0;
    }

    @Override
    public List<HistoryEntity> findAll() {
        return null;
    }

    @Override
    public HistoryEntity findById(int id) {
        return null;
    }
}
