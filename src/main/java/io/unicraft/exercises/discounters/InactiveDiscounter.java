package io.unicraft.exercises.discounters;

import io.unicraft.exercises.models.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static java.math.BigDecimal.ZERO;

public class InactiveDiscounter implements Discounter {

    @Override
    public BigDecimal calculateDiscount(Map<ProductRepository, Integer> productCountMap, LocalDate shoppingDate) {
        return ZERO;
    }
}
