package com.hoangbuix.dev.service;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface GoodsReceiptReport {
    void writeHeaderLine();

    void createCell(Row row, int columnCount, Object value, CellStyle style);

    void writeDataLines();

    void export(HttpServletResponse response) throws IOException;
}
