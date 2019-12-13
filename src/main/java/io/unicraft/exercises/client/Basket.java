package io.unicraft.exercises.client;

import io.unicraft.exercises.client.model.ProductRepository;

import java.time.LocalDate;
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
}
