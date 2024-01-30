package org.example.parser;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.example.Product;
import org.example.ProductSpreadsheetColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.example.ProductSpreadsheetColumn.*;

public class FastExcelParser implements SpreadsheetParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastExcelParser.class);
    private static final int HEADER = 1;

    private final Reader<ReadableWorkbook> reader;

    public FastExcelParser(Reader<ReadableWorkbook> reader) {
        this.reader = reader;
    }

    @Override
    public List<Product> parseFile(String path) {

        try (ReadableWorkbook readableWorkbook = reader.readFile(path)) {
            Optional<Sheet> optionalSheet = readableWorkbook.getSheet(0);
            if (optionalSheet.isPresent()) {

                Sheet sheet = optionalSheet.get();
                return sheet.openStream()
                        .skip(HEADER)
                        .map(this::convertToProduct)
                        .toList();
            }
        } catch (Exception ex) {
            LOGGER.error("Error when parsing xlsx file ", ex);
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    private Product convertToProduct(Row row) {
        return Product.builder()
                .name(getCellValue(NAME, row))
                .category(getCellValue(CATEGORY, row))
                .amount(Integer.parseInt(getCellValue(AMOUNT, row)))
                .price(Double.parseDouble(getCellValue(PRICE, row)))
                .warehouse(getCellValue(NAME, row))
                .build();
    }

    private String getCellValue(ProductSpreadsheetColumn column, Row row) {
        Cell cell = row.getCell(column.getOrder());
        return cell.getRawValue();
    }
}
