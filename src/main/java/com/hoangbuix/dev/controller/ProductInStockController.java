package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.ProductInStockEntity;
import com.hoangbuix.dev.service.ProductInStockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductInStockController {
    @Autowired
    private ProductInStockService productInStockService;

    static final Logger log = Logger.getLogger(ProductInStockController.class);

    @GetMapping("/product-in-stock/list")
    public ResponseEntity<?> findAll(){
        List<ProductInStockEntity> productInStock = productInStockService.findAll();
        return new ResponseEntity<>(productInStock.isEmpty() ? null : productInStock.get(0),HttpStatus.OK);
    }
}
