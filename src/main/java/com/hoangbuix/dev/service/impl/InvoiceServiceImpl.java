package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.InvoiceDAO;
import com.hoangbuix.dev.dao.ProductDAO;
import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.model.request.create.CreateInvoiceReq;
import com.hoangbuix.dev.service.HistoryService;
import com.hoangbuix.dev.service.InvoiceService;
import com.hoangbuix.dev.service.ProductInStockService;
import com.hoangbuix.dev.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class InvoiceServiceImpl implements InvoiceService {
    final Logger log = Logger.getLogger(InvoiceServiceImpl.class);
    @Autowired
    private ProductInStockService productInStockService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private InvoiceDAO<InvoiceEntity> invoiceDAO;

    @Autowired
    private ProductDAO<ProductInfoEntity> productDAO;

    @Override
    public InvoiceEntity save(CreateInvoiceReq req) {

        InvoiceEntity invoice = new InvoiceEntity();
        Object obj = invoiceDAO.findByCode(req.getCode());
        ProductInfoEntity productInfo = productDAO.findProductInfoById(req.getProductId());
        if (obj == null) {
            invoice.setCode(req.getCode());
            invoice.setType(req.getType());
            invoice.setProductInfos(productInfo);
//                invoice.setProductId(productId);
            invoice.setQty(req.getQty());
            invoice.setPrice(req.getPrice());
            invoice.setToDate(req.getToDate());
            invoice.setFromDate(req.getFromDate());
            invoice.setActiveFlag(1);
            invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            invoice.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        }
        int id = invoiceDAO.save(invoice);
        historyService.save(invoice, Constant.ACTION_ADD);
        productInStockService.saveOrUpdate(invoice);

        return invoiceDAO.findById(id);
    }

    @Override
    public void update(CreateInvoiceReq req) {
        try {
            InvoiceEntity invoice = new InvoiceEntity();
            int originQty = invoiceDAO.findByCode(req.getCode()).getQty();
            ProductInfoEntity productInfo = productDAO.findProductInfoById(req.getProductId());
            invoice.setCode(req.getCode());
            invoice.setType(req.getType());
            invoice.setProductInfos(productInfo);
            invoice.setQty(req.getQty());
            invoice.setPrice(req.getPrice());
            invoice.setToDate(req.getToDate());
            invoice.setFromDate(req.getFromDate());
            invoice.setActiveFlag(req.getActiveFlag());
            invoice.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            InvoiceEntity invoice2 = new InvoiceEntity();
            invoice2.setProductInfos(invoice.getProductInfos());
            invoice2.setQty(invoice.getQty() - originQty);
            invoice2.setPrice(invoice.getPrice());
            invoiceDAO.update(invoice);
            historyService.save(invoice, Constant.ACTION_EDIT);
            productInStockService.saveOrUpdate(invoice2);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }

    @Override
    public List<InvoiceEntity> findAll(int type) {
        return invoiceDAO.findAll(type);
    }

    @Override
    public InvoiceEntity findById(int id) {
        return invoiceDAO.findById(id);
    }

    @Override
    public InvoiceEntity findByCode(String code) {
        InvoiceEntity invoice = invoiceDAO.findByCode(code);
//        if (invoice== null) {
//            throw new NotFoundException("Not found with code = " + code);
//        }
        return invoice;
    }
}
