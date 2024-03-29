package org.example.parser;

import org.example.Product;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 20, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ReadBenchmarkTest {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public int apachePoi(BenchmarkInput input) {
        var apachePOIParser = new ApachePOIParser(new ApachePOIReader());
        List<Product> products = apachePOIParser.parseFile(input.pathToFile);
        return products.size();
    }

    @Benchmark
    public int fastExcel(BenchmarkInput input) {
        var fastExcelParser = new FastExcelParser(new FastExcelReader());
        List<Product> products = fastExcelParser.parseFile(input.pathToFile);
        return products.size();
    }

    @State(Scope.Benchmark)
    public static class BenchmarkInput {
        final String pathToFile  = "excel-import-benchmark.xlsx";
    }
}
