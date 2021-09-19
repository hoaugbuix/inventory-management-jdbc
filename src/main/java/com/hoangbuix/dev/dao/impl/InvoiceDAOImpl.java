package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.InvoiceDAO;
import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.model.mapper.InvoiceMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InvoiceDAOImpl extends BaseDAOImpl<InvoiceEntity> implements InvoiceDAO<InvoiceEntity> {
    final static Logger log = Logger.getLogger(InvoiceDAOImpl.class);

    @Override
    public int save(InvoiceEntity invoice) {
        log.info("save invoice" + invoice.toString());
        String sql = "call invoice_create(?,?,?,?,?,?,?,?,?,?)";
        return insert(sql, invoice.getCode(), invoice.getType(), invoice.getProductInfos().getId(), invoice.getQty(), invoice.getPrice(),
                invoice.getToDate(), invoice.getFromDate(), invoice.getActiveFlag(), invoice.getCreatedDate(), invoice.getUpdatedDate());
    }

    @Override
    public void update(InvoiceEntity invoice) {
        String sql = "call invoice_update(?,?,?,?,?,?,?,?,?)";
        update(sql, invoice.getCode(), invoice.getType(), invoice.getProductInfos().getId(), invoice.getQty(),
                invoice.getPrice(), invoice.getToDate(), invoice.getFromDate(), invoice.getActiveFlag(), invoice.getUpdatedDate());
    }

    @Override
    public InvoiceEntity findById(int id) {
        String sql = "call invoice_findById(?)";
        List<InvoiceEntity> invoice = query(sql, new InvoiceMapper(), id);
        return invoice.isEmpty() ? null : invoice.get(0);
    }

    @Override
    public InvoiceEntity findByCode(String code) {
        log.info("Find By Code = " + code);
        String sql = "call invoice_findByCode(?)";
        List<InvoiceEntity> invoice = query(sql, new InvoiceMapper(), code);
        return invoice.isEmpty() ? null : invoice.get(0);
    }

    @Override
    public List<InvoiceEntity> findAll(int type) {
        log.info("Find All");
        String sql = "call invoice_findAll(?)";
        return query(sql, new InvoiceMapper(), type);
    }
}
