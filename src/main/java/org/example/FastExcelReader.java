package org.example;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;

public class FastExcelReader implements Reader<ReadableWorkbook> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastExcelReader.class);

    public ReadableWorkbook readFile(String file) {
        try (InputStream stream = new FileInputStream(file)) {
            return new ReadableWorkbook(stream);
        } catch (Exception ex) {
            LOGGER.error("Error when reading xlsx file ", ex);
            throw new RuntimeException(ex);
        }
    }
}
