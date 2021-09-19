package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.HistoryDAO;
import com.hoangbuix.dev.dao.ProductDAO;
import com.hoangbuix.dev.entity.HistoryEntity;
import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.service.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class HistoryServiceImpl implements HistoryService {
    final Logger log = Logger.getLogger(HistoryServiceImpl.class);
    @Autowired
    private HistoryDAO<HistoryEntity> historyDAO;

    @Autowired
    private ProductDAO<ProductInfoEntity> productDAO;

    @Override
    public HistoryEntity save(InvoiceEntity invoice, String action) {
        int id = 0;
        try {
            HistoryEntity history = new HistoryEntity();
            ProductInfoEntity productInfo = productDAO.findProductInfoByCode(invoice.getProductInfos().getCode());
            history.setActionName(action);
            history.setType(invoice.getType());
            history.setProductInfo(productInfo);
            history.setQty(invoice.getQty());
            history.setPrice(invoice.getPrice());
            history.setActiveFlag(1);
            history.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            history.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            id = historyDAO.save(history);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
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
