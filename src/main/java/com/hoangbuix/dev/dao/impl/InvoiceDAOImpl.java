package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.InvoiceDAO;
import com.hoangbuix.dev.entity.InvoiceEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InvoiceDAOImpl extends BaseDAOImpl<InvoiceEntity> implements InvoiceDAO<InvoiceEntity> {
    @Override
    public int save(InvoiceEntity instance) {
        return 0;
    }

    @Override
    public void update(InvoiceEntity instance) {

    }

    @Override
    public InvoiceEntity findById(int id) {
        return null;
    }

    @Override
    public List<InvoiceEntity> findAll() {
        return null;
    }
}
