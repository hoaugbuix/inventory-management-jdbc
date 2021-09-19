package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.dao.ProductDAO;
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
    @Autowired
    private ProductDAO<ProductInfoEntity> productDAO;

    @Override
    public ProductInStockEntity saveOrUpdate(InvoiceEntity invoice) {
        log.info("product in stock ");
        int newId = 0;
        if (invoice.getProductInfos() != null) {
            int id = invoice.getProductInfos().getId();
            ProductInStockEntity product = productInStockDAO.findById(id);
//            ProductInStockEntity product=null;
            log.info("id == " + id);
            if (product != null) {
//                product = products;
                log.info("update qty=" + invoice.getQty() + " and price=" + invoice.getPrice());
                if (invoice.getType() == 2) {
                    product.setQty(product.getQty() - invoice.getQty());
                } else {
                    product.setQty(product.getQty() + invoice.getQty());
                }

                // type =1 receipt , type =2 issues
                if (invoice.getType() == 1) {
                    product.setPrice(invoice.getPrice());
                }
                product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                productInStockDAO.update(product);

            } else if (invoice.getType() == 1) {
                log.info("insert to stock qty=" + invoice.getQty() + " and price=" + invoice.getPrice());
                product = new ProductInStockEntity();
                ProductInfoEntity productInfo = productDAO.findProductInfoById(invoice.getProductInfos().getId());
                product.setProductInfos(productInfo);
                product.setActiveFlag(1);
                product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                product.setQty(invoice.getQty());
                product.setPrice(invoice.getPrice());
                newId = productInStockDAO.save(product);
            }
        }

        return productInStockDAO.findById(newId);
    }

    @Override
    public List<ProductInStockEntity> findAll() {
        return productInStockDAO.findAll();
    }
}
