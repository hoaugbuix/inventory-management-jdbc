package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.entity.HistoryEntity;
import com.hoangbuix.dev.service.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {
    static final Logger log = Logger.getLogger(HistoryController.class);

    @Autowired
    private HistoryService historyService;

    @GetMapping("/history/list")
    public ResponseEntity<?> findAll(){
        List<HistoryEntity> history =historyService.findAll();
        return new ResponseEntity<>(history.isEmpty() ? null : history, HttpStatus.OK);
    }
}
