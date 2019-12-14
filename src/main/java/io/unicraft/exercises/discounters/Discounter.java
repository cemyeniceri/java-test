package io.unicraft.exercises.discounters;

import io.unicraft.exercises.models.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface Discounter {
    BigDecimal calculateDiscount(Map<ProductRepository, Integer> productCountMap, LocalDate shoppingDate);
}
