package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.ProductInStockDAO;
import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.entity.ProductInStockEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.service.ProductInStockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ProductInStockServiceImpl implements ProductInStockService {
    final Logger log = Logger.getLogger(ProductInStockServiceImpl.class);
    @Autowired
    private ProductInStockDAO<ProductInStockEntity> productInStockDAO;

    @Override
    public ProductInStockEntity saveOrUpdate(InvoiceEntity invoice) {
        log.info("product in stock");
        int id = 0;
        if (invoice.getProductInfos() != null) {
            String code = invoice.getProductInfos().getCode();
            ProductInStockEntity product = productInStockDAO.findByCode(code);
            if (product != null) {
                log.info("updated qty=" + invoice.getQty() + " and price=" + invoice.getPrice());
                product.setQty(invoice.getQty());
                product.setPrice(invoice.getPrice());
                product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                productInStockDAO.update(product);
            } else {
                log.info("insert product in stock=" + invoice.getQty() + " and price=" + invoice.getPrice());
                product = new ProductInStockEntity();
                ProductInfoEntity productInfo = new ProductInfoEntity();
//                productInfo.setId(invoice.getId());
                product.setProductInfos(productInfo);
                product.setQty(invoice.getQty());
                product.setPrice(invoice.getPrice());
                product.setActiveFlag(1);
                product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                id = productInStockDAO.save(product);
            }
        }
        return id != 0 ? productInStockDAO.findById(id) : null;
    }

    @Override
    public List<ProductInStockEntity> findAll() {
        return productInStockDAO.findAll();
    }
}
