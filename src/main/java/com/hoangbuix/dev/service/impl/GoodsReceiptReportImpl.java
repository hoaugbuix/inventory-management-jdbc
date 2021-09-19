package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.entity.InvoiceEntity;
import com.hoangbuix.dev.service.GoodsReceiptReport;
import com.hoangbuix.dev.util.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class GoodsReceiptReportImpl extends AbstractXlsView implements GoodsReceiptReport {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<InvoiceEntity> listInvoices;

    public GoodsReceiptReportImpl(List<InvoiceEntity> listInvoices) {
        this.listInvoices = listInvoices;
        workbook = new XSSFWorkbook();
    }

    @Override
    public void writeHeaderLine() {

        sheet = workbook.createSheet("Datas");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Code", style);
        createCell(row, 2, "Qty", style);
        createCell(row, 3, "Price", style);
        createCell(row, 4, "Product", style);
        createCell(row, 5, "Updated Date", style);
    }

    @Override
    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    @Override
    public void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (InvoiceEntity invoice : listInvoices) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, invoice.getId(), style);
            createCell(row, columnCount++, invoice.getQty(), style);
            createCell(row, columnCount++, invoice.getPrice().toString(), style);
            createCell(row, columnCount++, invoice.getProductInfos().toString(), style);
            createCell(row, columnCount++, DateUtil.dateToString(invoice.getUpdatedDate()), style);
        }
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

    }
}
