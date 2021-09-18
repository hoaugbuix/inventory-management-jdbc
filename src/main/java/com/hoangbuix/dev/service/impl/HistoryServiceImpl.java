package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.HistoryDAO;
import com.hoangbuix.dev.dao.ProductDAO;
import com.hoangbuix.dev.entity.HistoryEntity;
import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDAO<HistoryEntity> historyDAO;

    @Autowired
    private ProductDAO<ProductInfoEntity> productDAO;

    @Override
    public HistoryEntity save(InvoiceEntity invoice, String action) {
        HistoryEntity history = new HistoryEntity();
        ProductInfoEntity productInfo = productDAO.findProductInfoById(invoice.getProductId());
        history.setActionName(action);
        history.setType(invoice.getType());
        history.setProductInfo(productInfo);
        history.setQty(invoice.getQty());
        history.setPrice(invoice.getPrice());
        history.setActiveFlag(1);
        history.setCreatedDate(new Date(System.currentTimeMillis()));
        history.setUpdatedDate(new Date(System.currentTimeMillis()));
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
