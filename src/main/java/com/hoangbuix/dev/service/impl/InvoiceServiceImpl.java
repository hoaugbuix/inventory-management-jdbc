package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.InvoiceDAO;
import com.hoangbuix.dev.dao.ProductDAO;
import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.model.request.create.CreateInvoiceReq;
import com.hoangbuix.dev.service.HistoryService;
import com.hoangbuix.dev.service.InvoiceService;
import com.hoangbuix.dev.service.ProductInStockService;
import com.hoangbuix.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class InvoiceServiceImpl implements InvoiceService {
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
        ProductInfoEntity productInfo = productDAO.findProductInfoByCode(req.getProductCode());
        invoice.setCode(req.getCode());
        invoice.setType(req.getType());
        invoice.setProductInfos(productInfo);
        invoice.setActiveFlag(1);
        invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        invoice.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        int id = invoiceDAO.save(invoice);
        historyService.save(invoice, Constant.ACTION_ADD);
        productInStockService.saveOrUpdate(invoice);
        return invoiceDAO.findById(id);
    }

    @Override
    public void update(CreateInvoiceReq invoice) {
//        int originQty = invoiceDAO.findById(invoice.getId()).getQty();
//        ProductInfoEntity productInfo = productDAO.findProductInfoByCode(invoice.getProductInfos().getCode());
//        invoice.setProductInfos(productInfo);
//        invoice.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
//        InvoiceEntity invoice2 = new InvoiceEntity();
//        invoice2.setProductInfos(invoice.getProductInfos());
//        invoice2.setQty(invoice.getQty() - originQty);
//        invoice2.setPrice(invoice.getPrice());
//        invoiceDAO.update(invoice);
//        historyService.save(invoice, Constant.ACTION_EDIT);
//        productInStockService.saveOrUpdate(invoice2);
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
        return null;
    }
}
