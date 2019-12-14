package io.unicraft.exercises.discounters;

import io.unicraft.exercises.models.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

import static io.unicraft.exercises.models.ProductRepository.BREAD;
import static io.unicraft.exercises.models.ProductRepository.SOUP;
import static java.math.BigDecimal.ZERO;

public class BreadDiscounter implements Discounter {

    private static final LocalDate startTime = LocalDate.now().minusDays(1);
    private static final LocalDate endDate = startTime.plusDays(7);

    @Override
    public BigDecimal calculateDiscount(Map<ProductRepository, Integer> productCountMap, LocalDate shoppingDate) {
        if (isShoppingDateValid(shoppingDate) && productCountMap.containsKey(SOUP)) {
            Integer soupCount = productCountMap.get(SOUP);
            Integer breadCount = productCountMap.get(BREAD);
            int toBeDiscountedBreadCount = soupCount / 2 > breadCount ? breadCount : soupCount / 2;
            return BREAD.getPrice().multiply(BigDecimal.valueOf(toBeDiscountedBreadCount)).divide(BigDecimal.valueOf(2), 2, RoundingMode.CEILING);
        } else {
            return ZERO;
        }
    }

    private boolean isShoppingDateValid(LocalDate shoppingDate) {
        return !shoppingDate.isBefore(startTime) && !shoppingDate.isAfter(endDate);
    }
}
