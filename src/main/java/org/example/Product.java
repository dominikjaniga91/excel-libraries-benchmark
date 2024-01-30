package org.example;

import lombok.Builder;

@Builder
public record Product(String name, String category, int amount, double price, String warehouse) {

}
