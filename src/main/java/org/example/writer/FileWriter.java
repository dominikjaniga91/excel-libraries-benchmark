package org.example.writer;

import org.example.Product;

import java.util.List;

interface FileWriter {

    void writeFile(List<Product> productList);
}
