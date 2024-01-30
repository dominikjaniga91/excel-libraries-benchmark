package org.example.parser;

import org.example.Product;

import java.util.List;

public interface SpreadsheetParser {

    List<Product> parseFile(String path);
}
