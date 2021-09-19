package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.exception.NotFoundException;
import com.hoangbuix.dev.model.request.create.CreateInvoiceReq;
import com.hoangbuix.dev.service.InvoiceService;
import com.hoangbuix.dev.service.ProductInfoService;
import com.hoangbuix.dev.service.impl.GoodsReceiptReportImpl;
import com.hoangbuix.dev.util.Constant;
import com.hoangbuix.dev.validate.InvoiceValidator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GoodsReceiptController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private ProductInfoService productInfoService;

    static final Logger log = Logger.getLogger(GoodsReceiptController.class);

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        if (binder.getTarget() == null) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        if (binder.getTarget().getClass() == InvoiceEntity.class) {
            binder.setValidator(invoiceValidator);
        }
    }

    @GetMapping("/goods-receipt/list")
    private ResponseEntity<?> getAll() {
        log.info("show list invoice");
        List<InvoiceEntity> invoices = invoiceService.findAll(Constant.TYPE_GOODS_RECEIPT);
        if (invoices.isEmpty()) {
            throw new NotFoundException("invoice is null");
        }
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/goods-receipt/save")
    private ResponseEntity<?> save(@Valid @RequestBody CreateInvoiceReq req) {
        log.info("code = " + req.getCode());
        InvoiceEntity invoice = invoiceService.findByCode(req.getCode());
        InvoiceEntity result = null;
        req.setType(Constant.TYPE_GOODS_RECEIPT);
        if (invoice != null && req.getActiveFlag() != 0) {
            log.info("update invoice");
            try {
                invoiceService.update(req);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        } else {
            try {
                result = invoiceService.save(req);
            } catch (Exception e2) {
                e2.printStackTrace();
                log.info(e2.getMessage());
            }
        }
        return new ResponseEntity<>(result != null ? result : "Update success", HttpStatus.OK);
    }

    @PutMapping("/goods-receipt/edit/{id}")
    private ResponseEntity<?> edit(@PathVariable int id, @RequestBody CreateInvoiceReq invoice) {
        try {
            log.info("Edit invoice with id=" + id);
            InvoiceEntity result = invoiceService.findById(id);
            if (result != null) {
                result.setActiveFlag(invoice.getActiveFlag());
                invoiceService.update(invoice);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return new ResponseEntity<>("Update success!", HttpStatus.OK);
    }


    @GetMapping("/goods-receipt/view/{id}")
    private ResponseEntity<?> findById(@PathVariable int id) {
        InvoiceEntity invoice = invoiceService.findById(id);
        if (invoice.getCode() == null) {
            throw new NotFoundException("No find " + id);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping("/goods-receipt/view-code/{code}")
    private ResponseEntity<?> findByCode(@PathVariable String code) {
        InvoiceEntity invoice = invoiceService.findByCode(code);
        if (invoice.getCode() == null) {
            throw new NotFoundException("No find " + code);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping("/goods-receipt/export")
    private ResponseEntity<?> export(HttpServletResponse response) throws IOException {
        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setType(Constant.TYPE_GOODS_RECEIPT);
        List<InvoiceEntity> listInvoice = invoiceService.findAll(invoice.getType());
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        GoodsReceiptReportImpl goodsReceiptReport = new GoodsReceiptReportImpl(listInvoice);
        goodsReceiptReport.export(response);
        return new ResponseEntity<>("export success!", HttpStatus.OK);
    }

    private Map<String, String> initMapProduct() {
        List<ProductInfoEntity> productInfos = productInfoService.findAll();
        Map<String, String> mapProduct = new HashMap<String, String>();
        for (ProductInfoEntity productInfo : productInfos) {
            mapProduct.put(productInfo.getId().toString(), productInfo.getName());
        }

        return mapProduct;
    }
}
