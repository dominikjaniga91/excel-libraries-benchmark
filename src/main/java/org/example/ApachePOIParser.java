package org.example;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.example.ProductSpreadsheetColumn.*;

public class ApachePOIParser implements SpreadsheetParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApachePOIParser.class);

    private final DataFormatter formatter = new DataFormatter();
    private final Reader<Workbook> reader;

    public ApachePOIParser(Reader<Workbook> reader) {
        this.reader = reader;
    }

    @Override
    public List<Product> parseFile(String path) {
        List<Product> products = new ArrayList<>();
        try (Workbook workbook = reader.readFile(path)) {
            Sheet sheet = workbook.getSheetAt(0);
            int firstRow = 1;
            for (int i = firstRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Product product = Product.builder()
                        .name(getCellValue(NAME, row))
                        .category(getCellValue(CATEGORY, row))
                        .amount(Integer.parseInt(getCellValue(AMOUNT, row)))
                        .price(Double.parseDouble(getCellValue(PRICE, row)))
                        .warehouse(getCellValue(NAME, row))
                        .build();
                products.add(product);
            }
            return products;
        } catch (Exception ex) {
            LOGGER.error("Error when parsing xlsx file ", ex);
            return Collections.emptyList();
        }
    }

    private String getCellValue(ProductSpreadsheetColumn column, Row row) {
        Cell cell = row.getCell(column.getOrder());
        return formatter.formatCellValue(cell);
    }
}