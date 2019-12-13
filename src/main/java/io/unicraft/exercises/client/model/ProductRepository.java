package io.unicraft.exercises.client.model;

import java.math.BigDecimal;

public enum ProductRepository {
    SOUP(1, "Soup", "tin", BigDecimal.valueOf(0.65)),
    BREAD(2, "Bread", "loaf", BigDecimal.valueOf(0.80)),
    MILK(3, "Milk", "bottle", BigDecimal.valueOf(1.30)),
    APPLE(4, "Apple", "single", BigDecimal.valueOf(0.10));

    private int id;
    private String name;
    private String unit;
    private BigDecimal price;

    ProductRepository(int id, String name, String unit, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public static ProductRepository findById(int id) {
        for (ProductRepository product : values()) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
