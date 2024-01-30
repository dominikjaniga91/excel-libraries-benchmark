package org.example.parser;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;

public class ApachePOIReader implements Reader<Workbook> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApachePOIReader.class);

    public Workbook readFile(String file) {
        try (InputStream stream = new FileInputStream(file)) {
            return WorkbookFactory.create(stream);
        } catch (Exception ex) {
            LOGGER.error("Error when reading xlsx file ", ex);
            throw new RuntimeException(ex);
        }
    }
}
