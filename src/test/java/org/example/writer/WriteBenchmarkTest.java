package org.example.writer;

import org.example.Product;
import org.example.TestData;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 20, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class WriteBenchmarkTest {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void apachePoi(BenchmarkInput input) {
        var apachePOIWriter = new ApachePOIWriter();
        apachePOIWriter.writeFile(input.productList);
    }

    @Benchmark
    public void fastExcel(BenchmarkInput input) {
        var fastExcelWriter = new FastExcelWriter();
        fastExcelWriter.writeFile(input.productList);
    }

    @State(Scope.Benchmark)
    public static class BenchmarkInput {
        final List<Product> productList = TestData.productList;
    }
}
