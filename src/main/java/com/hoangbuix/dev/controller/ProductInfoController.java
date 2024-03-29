package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.exception.DuplicateRecordException;
import com.hoangbuix.dev.model.request.create.CreateProductInfoReq;
import com.hoangbuix.dev.model.request.update.UpdateProductInfoReq;
import com.hoangbuix.dev.service.ProductInfoService;
import com.hoangbuix.dev.validate.ProductInfoValidator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class ProductInfoController {
    final Logger log = Logger.getLogger(ProductInfoController.class);
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductInfoValidator productInfoValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        if (binder.getTarget() == null) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        if (binder.getTarget().getClass() == ProductInfoEntity.class) {
            binder.setValidator(productInfoValidator);
        }
    }


    @PostMapping("/product-info/save")
    private ResponseEntity<?> saveProductInfo(@Valid @RequestBody CreateProductInfoReq req) {
        log.info(" product = " + req.toString());
        Object obj = productInfoService.findByCode(req.getCode());
        ProductInfoEntity result = null;
        if (obj == null) {
            result = productInfoService.save(req);
        } else {
            throw new DuplicateRecordException("Đã tồn tại");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/product-info/edit/{id}")
    private ResponseEntity<?> editProductInfo(@PathVariable int id, @Valid @RequestBody UpdateProductInfoReq req) {
        productInfoService.update(id, req);
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @GetMapping("/product-info/delete/{id}")
    private ResponseEntity<?> deleteProductInfo(@PathVariable int id) {
        productInfoService.delete(id);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @GetMapping("/product-info/list")
    private ResponseEntity<?> getAllProductInfo() {
        List<ProductInfoEntity> product = productInfoService.findAll();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product-info/view/{id}")
    private ResponseEntity<?> view(@PathVariable int id) {
        ProductInfoEntity productInfo = productInfoService.findById(id);
        return new ResponseEntity<>(productInfo, HttpStatus.OK);
    }
}
