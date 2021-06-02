package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.HistoryDAO;
import com.hoangbuix.dev.entity.HistoryEntity;
import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDAO<HistoryEntity> historyDAO;

    @Override
    public HistoryEntity save(InvoiceEntity invoice, String action) {
        HistoryEntity history = new HistoryEntity();
        int id = historyDAO.save(history);
        return historyDAO.findById(id);
    }

    @Override
    public List<HistoryEntity> findAll() {
        return historyDAO.findAll();
    }

    @Override
    public HistoryEntity findById(int id) {
        return historyDAO.findById(id);
    }
}
