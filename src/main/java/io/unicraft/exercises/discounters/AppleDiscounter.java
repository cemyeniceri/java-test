package io.unicraft.exercises.discounters;

import io.unicraft.exercises.models.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

import static io.unicraft.exercises.models.ProductRepository.APPLE;
import static java.math.BigDecimal.ZERO;

public class AppleDiscounter implements Discounter {

    private static final BigDecimal discountPercentage = BigDecimal.valueOf(10);
    private static final BigDecimal divisor = BigDecimal.valueOf(100);
    private static final LocalDate startTime = LocalDate.now().plusDays(3);
    private static final LocalDate endDate = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

    @Override
    public BigDecimal calculateDiscount(Map<ProductRepository, Integer> productCountMap, LocalDate shoppingDate) {
        if (isShoppingDateValid(shoppingDate))
            return APPLE.getPrice().multiply(BigDecimal.valueOf(productCountMap.get(APPLE))).multiply(discountPercentage).divide(divisor, 2, RoundingMode.CEILING);
        else
            return ZERO;
    }

    private boolean isShoppingDateValid(LocalDate shoppingDate) {
        return !shoppingDate.isBefore(startTime) && !shoppingDate.isAfter(endDate);
    }
}
