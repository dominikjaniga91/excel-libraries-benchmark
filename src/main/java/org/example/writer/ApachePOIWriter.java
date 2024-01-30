package org.example.writer;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Product;
import org.example.ProductSpreadsheetColumn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Function;

class ApachePOIWriter implements FileWriter {

    private static final String PATH = "excel-import-benchmark1.xls";
    private static final int HEADER = 0;
    private static final int NUMBER_OF_HEADER_ROWS = 1;

    @Override
    public void writeFile(List<Product> productList) {

        try (OutputStream outputStream = new FileOutputStream(PATH);
             Workbook workbook = new XSSFWorkbook()) {
            Sheet dataSheet = workbook.createSheet("Products");
            writeWorksheetHeader(dataSheet);
            writeWorksheetValues(productList, dataSheet);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void writeWorksheetValues(List<Product> productList, Sheet dataSheet) {
        for (int rowNumber = 0; rowNumber < productList.size(); rowNumber++) {
            Product productToWrite = productList.get(rowNumber);
            Row row = dataSheet.createRow(rowNumber + NUMBER_OF_HEADER_ROWS);
            for (var productMap : ProductSpreadsheetColumn.getProductMap().entrySet()) {
                int columnNumber = productMap.getKey().getOrder();
                Cell cell = row.createCell(columnNumber);
                Function<Product, String> productFunction = productMap.getValue();
                String valueToWrite = productFunction.apply(productToWrite);
                cell.setCellValue(valueToWrite);
            }
        }
    }

    private void writeWorksheetHeader(Sheet dataSheet) {
        for (ProductSpreadsheetColumn column : ProductSpreadsheetColumn.values()) {
            Row row = dataSheet.createRow(HEADER);
            Cell cell = row.createCell(column.getOrder());
            cell.setCellValue(column.name());
        }
    }
}
