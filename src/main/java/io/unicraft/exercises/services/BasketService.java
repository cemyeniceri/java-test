package io.unicraft.exercises.services;

import io.unicraft.exercises.models.ProductRepository;
import io.unicraft.exercises.models.SalesSlip;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
        for (Map.Entry<ProductRepository, Integer> productCountPair : productCountMap.entrySet()) {
            totalPrice = totalPrice.add(productCountPair.getKey().getPrice().multiply(BigDecimal.valueOf(productCountPair.getValue())));
        }

        BigDecimal totalDiscount = productCountMap.keySet().stream()
                .map(product -> product.getDiscounter().calculateDiscount(productCountMap, shoppingDate))
                .reduce(ZERO, BigDecimal::add);

        return new SalesSlip(totalPrice, totalDiscount, totalPrice.subtract(totalDiscount));
    }

    public void printDetails() {
        System.out.println("Shopping date is : " + shoppingDate);
        System.out.println("Products : ");
        System.out.println(productCountMap);
    }
}
