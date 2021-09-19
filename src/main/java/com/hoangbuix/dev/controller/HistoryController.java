package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.HistoryEntity;
import com.hoangbuix.dev.exception.NotFoundException;
import com.hoangbuix.dev.service.HistoryService;
import com.hoangbuix.dev.util.Constant;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HistoryController {
    static final Logger log = Logger.getLogger(HistoryController.class);

    @Autowired
    private HistoryService historyService;

    @GetMapping("/history/list")
    public ResponseEntity<?> findAll() {
        log.info("show list histories");
        List<HistoryEntity> history = historyService.findAll();
        if (history.isEmpty()) {
            throw new NotFoundException("history is null");
        }
        Map<String, String> mapType = new HashMap<>();
        mapType.put(String.valueOf(Constant.TYPE_ALL), "All");
        mapType.put(String.valueOf(Constant.TYPE_GOODS_RECEIPT), "Goods Receipt");
        mapType.put(String.valueOf(Constant.TYPE_GOODS_ISSUES), "Goods Issues");
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
