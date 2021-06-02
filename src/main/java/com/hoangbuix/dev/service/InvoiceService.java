package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.InvoiceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {
    InvoiceEntity save(InvoiceEntity invoice);
    void update(InvoiceEntity invoice, int id);
    List<InvoiceEntity> findAll();
    InvoiceEntity findById(int id);
}
