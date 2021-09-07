package com.hoangbuix.dev.service;

import com.hoangbuix.dev.entity.HistoryEntity;
import com.hoangbuix.dev.entity.InvoiceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoryService {
    HistoryEntity save(InvoiceEntity invoice, String action);

    List<HistoryEntity> findAll();

    HistoryEntity findById(int id);
}
