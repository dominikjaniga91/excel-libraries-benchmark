package org.example;

import java.util.List;

class Program {
    public static void main(String[] args) {
        var apachePOIParser = new ApachePOIParser(new ApachePOIReader());
        List<Product> apachePoi = apachePOIParser.parseFile("excel-import-benchmark.xlsx");
        System.out.println(apachePoi);

        var fastExcelParser = new FastExcelParser(new FastExcelReader());
        List<Product> fastExcel = fastExcelParser.parseFile("excel-import-benchmark.xlsx");
        System.out.println(fastExcel);
    }
}
