package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.model.request.CreateProductInfoReq;
import com.hoangbuix.dev.service.ProductInfoService;
import com.hoangbuix.dev.validate.ProductInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProductInfoController {
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
    private ResponseEntity<?> saveProductInfo(@Valid @RequestBody CreateProductInfoReq req){
        ProductInfoEntity product = productInfoService.save(req);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product-info/all")
    private ResponseEntity<?> getAllProductInfo(){
        List<ProductInfoEntity> product = productInfoService.findAll();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
