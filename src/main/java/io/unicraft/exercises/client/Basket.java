package io.unicraft.exercises.client;

import io.unicraft.exercises.client.model.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

public class Basket {

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

    public BigDecimal calculateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map.Entry<ProductRepository, Integer> productCountPair : productCountMap.entrySet()) {
            totalPrice = totalPrice.add(productCountPair.getKey().getPrice().multiply(BigDecimal.valueOf(productCountPair.getValue())));
        }

        if (productCountMap.containsKey(ProductRepository.APPLE)) {
            if (!shoppingDate.isBefore(LocalDate.now().plusDays(3)) && !shoppingDate.isAfter(LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()))) {
                BigDecimal discountAmount = totalPrice.multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100));
                return totalPrice.subtract(discountAmount);
            }
        }
        return totalPrice;
    }

    public void printDetails() {
        System.out.println("Shopping date is : " + shoppingDate);
        System.out.println("Products : ");
        System.out.println(productCountMap);
    }
}
