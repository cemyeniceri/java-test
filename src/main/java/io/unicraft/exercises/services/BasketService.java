package io.unicraft.exercises.services;

import io.unicraft.exercises.models.ProductRepository;
import io.unicraft.exercises.models.SalesSlip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

import static io.unicraft.exercises.models.ProductRepository.*;
import static java.math.BigDecimal.ZERO;

public class BasketService {

    private Map<ProductRepository, Integer> productCountMap = new HashMap<>();
    private LocalDate shoppingDate;

    public LocalDate getShoppingDate() {
        return shoppingDate;
    }

    public void setShoppingDate(LocalDate shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public void addProduct(ProductRepository product, int count) {
        if (productCountMap.containsKey(product)) {
            Integer currentCount = productCountMap.get(product);
            productCountMap.put(product, currentCount + count);
        } else {
            productCountMap.put(product, count);
        }
    }

    public Map<ProductRepository, Integer> getProductCountMap() {
        return productCountMap;
    }

    public void clearProductMap() {
        productCountMap.clear();
    }

    public SalesSlip calculateTotalPrice() {
        BigDecimal totalPrice = ZERO;
        BigDecimal discountPrice = ZERO;
        for (Map.Entry<ProductRepository, Integer> productCountPair : productCountMap.entrySet()) {
            totalPrice = totalPrice.add(productCountPair.getKey().getPrice().multiply(BigDecimal.valueOf(productCountPair.getValue())));
        }

        if (productCountMap.containsKey(APPLE)) {
            if (!shoppingDate.isBefore(LocalDate.now().plusDays(3)) && !shoppingDate.isAfter(LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()))) {
                BigDecimal discountAmount = APPLE.getPrice().multiply(BigDecimal.valueOf(productCountMap.get(APPLE))).multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING);
                discountPrice = discountPrice.add(discountAmount);
            }
        }

        if (productCountMap.containsKey(BREAD)) {
            if (!shoppingDate.isBefore(LocalDate.now().minusDays(1)) && !shoppingDate.isAfter(LocalDate.now().plusDays(6))) {
                if (productCountMap.containsKey(SOUP)) {
                    Integer soupCount = productCountMap.get(SOUP);
                    Integer breadCount = productCountMap.get(BREAD);
                    int toBeDiscountedBreadCount = soupCount / 2 > breadCount ? breadCount : soupCount / 2;
                    BigDecimal discountAmount = BREAD.getPrice().multiply(BigDecimal.valueOf(toBeDiscountedBreadCount)).divide(BigDecimal.valueOf(2), 2, RoundingMode.CEILING);
                    discountPrice = discountPrice.add(discountAmount);
                }
            }
        }
        return new SalesSlip(totalPrice, discountPrice, totalPrice.subtract(discountPrice));
    }

    public void printDetails() {
        System.out.println("Shopping date is : " + shoppingDate);
        System.out.println("Products : ");
        System.out.println(productCountMap);
    }
}
