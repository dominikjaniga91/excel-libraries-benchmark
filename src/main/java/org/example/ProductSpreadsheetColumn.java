package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSpreadsheetColumn {
    NAME(0),
    CATEGORY(1),
    AMOUNT(2),
    PRICE(3),
    WAREHOUSE(4);

    private final int order;
}