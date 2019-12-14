package io.unicraft.exercises.models;

import io.unicraft.exercises.discounters.AppleDiscounter;
import io.unicraft.exercises.discounters.BreadDiscounter;
import io.unicraft.exercises.discounters.Discounter;
import io.unicraft.exercises.discounters.InactiveDiscounter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum ProductRepository {
    SOUP(1, "Soup", "tin", BigDecimal.valueOf(0.65).setScale(2, RoundingMode.CEILING), new InactiveDiscounter()),
    BREAD(2, "Bread", "loaf", BigDecimal.valueOf(0.80).setScale(2, RoundingMode.CEILING), new BreadDiscounter()),
    MILK(3, "Milk", "bottle", BigDecimal.valueOf(1.30).setScale(2, RoundingMode.CEILING), new InactiveDiscounter()),
    APPLE(4, "Apple", "single", BigDecimal.valueOf(0.10).setScale(2, RoundingMode.CEILING), new AppleDiscounter());

    private int id;
    private String name;
    private String unit;
    private BigDecimal price;
    private Discounter discounter;

    ProductRepository(int id, String name, String unit, BigDecimal price, Discounter discounter) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.discounter = discounter;
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

    public Discounter getDiscounter() {
        return discounter;
    }
}
