package org.example.writer;

import org.dhatim.fastexcel.Position;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.example.Product;
import org.example.ProductSpreadsheetColumn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Function;

class FastExcelWriter implements FileWriter {

    private static final String PATH = "excel-import-benchmark1.xls";
    private static final int HEADER = 0;
    private static final int NUMBER_OF_HEADER_ROWS = 1;

    @Override
    public void writeFile(List<Product> productList) {

        try (OutputStream outputStream = new FileOutputStream(PATH);
             Workbook workbook = new Workbook(outputStream, "products", "1.0" )) {
            Worksheet dataSheet = workbook.newWorksheet("Data");
            dataSheet.header("Products", Position.LEFT);
            writeWorksheetHeader(dataSheet);
            writeWorksheetValues(productList, dataSheet);
            workbook.finish();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeWorksheetValues(List<Product> productList, Worksheet dataSheet) {
        for (int row = 0; row < productList.size(); row++) {
            Product productToWrite = productList.get(row);
            for (var productMap : ProductSpreadsheetColumn.getProductMap().entrySet()) {
                int column = productMap.getKey().getOrder();
                Function<Product, String> productFunction = productMap.getValue();
                String valueToWrite = productFunction.apply(productToWrite);
                dataSheet.value(row + NUMBER_OF_HEADER_ROWS, column, valueToWrite);
            }
        }
    }

    private void writeWorksheetHeader(Worksheet dataSheet) {
        for (ProductSpreadsheetColumn column : ProductSpreadsheetColumn.values()) {
            dataSheet.value(HEADER, column.getOrder(), column.name());
        }
    }
}
