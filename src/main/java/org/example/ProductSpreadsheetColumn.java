package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum ProductSpreadsheetColumn {
    NAME("Name", 0),
    CATEGORY("Category", 1),
    AMOUNT("Amount", 2),
    PRICE("Price", 3),
    WAREHOUSE("Warehouse", 4);

    private final String name;
    private final int order;

    public static Map<ProductSpreadsheetColumn, Function<Product, String>> getProductMap() {

        var columnToFunction = new EnumMap<ProductSpreadsheetColumn, Function<Product, String>>(ProductSpreadsheetColumn.class);
        columnToFunction.put(NAME, Product::name);
        columnToFunction.put(CATEGORY, Product::category);
        columnToFunction.put(AMOUNT, p -> String.valueOf(p.name()));
        columnToFunction.put(PRICE, p -> String.valueOf(p.price()));
        columnToFunction.put(WAREHOUSE, Product::warehouse);

        return columnToFunction;
    }

}