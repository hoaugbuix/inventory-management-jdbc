package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.model.request.create.CreateInvoiceReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {
    InvoiceEntity save(CreateInvoiceReq invoice);

    void update(CreateInvoiceReq invoice);

    List<InvoiceEntity> findAll(int type);

    InvoiceEntity findById(int id);

    InvoiceEntity findByCode(String code);
}
