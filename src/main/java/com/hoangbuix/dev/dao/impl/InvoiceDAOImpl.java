package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.InvoiceDAO;
import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.model.mapper.InvoiceMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InvoiceDAOImpl extends BaseDAOImpl<InvoiceEntity> implements InvoiceDAO<InvoiceEntity> {
    @Override
    public int save(InvoiceEntity invoice) {
        String sql = "call invoice_create(?,?,?,?,?,?,?,?,?)";
        return insert(sql, invoice.getCode(), invoice.getType(), invoice.getProductInfos(), invoice.getQty(), invoice.getPrice(),
                invoice.getFromDate(), invoice.getActiveFlag(), invoice.getCreatedDate(), invoice.getUpdatedDate());
    }

    @Override
    public void update(InvoiceEntity invoice) {
        String sql = "call invoice_update(?,?,?,?,?,?,?,?)";
        update(sql, invoice.getCode(), invoice.getType(), invoice.getProductInfos(), invoice.getQty(),
                invoice.getPrice(), invoice.getFromDate(), invoice.getActiveFlag(), invoice.getUpdatedDate());
    }

    @Override
    public InvoiceEntity findById(int id) {
        String sql = "call invoice_findById(?)";
        List<InvoiceEntity> invoice = query(sql, new InvoiceMapper(), id);
        return invoice.isEmpty() ? null : invoice.get(0);
    }

    @Override
    public List<InvoiceEntity> findAll(int type) {
        String sql = "call invoice_findAll(?)";
        return query(sql, new InvoiceMapper(), type);
    }
}
