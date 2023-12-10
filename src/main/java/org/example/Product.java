package org.example;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Product {

    private final String name;
    private final String category;
    private final int amount;
    private final double price;
    private final String warehouse;

    public Product(String name,
                   String category,
                   int amount,
                   double price,
                   String warehouse) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.price = price;
        this.warehouse = warehouse;
    }
}
