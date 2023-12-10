package org.example;

import java.util.List;

public interface SpreadsheetParser {

    List<Product> parseFile(String path);
}
