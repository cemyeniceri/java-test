package io.unicraft.exercises.client;

import io.unicraft.exercises.client.model.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static io.unicraft.exercises.client.model.ProductRepository.*;

public class BasketTest {

    private Basket basket;

    @Before
    public void setUp() {
        basket = new Basket();
    }

    @Test
    public void addProductToBasketTest() {
        Map<ProductRepository, Integer> productCountMap = basket.getProductCountMap();
        Assert.assertEquals(0, productCountMap.size());
        basket.addProduct(SOUP, 3);
        Assert.assertEquals(1, productCountMap.size());
        Assert.assertEquals(3, productCountMap.get(SOUP).intValue());
    }

    @Test
    public void shouldClearProductCountMap() {
        basket.addProduct(SOUP, 3);
        basket.addProduct(MILK, 6);
        basket.addProduct(BREAD, 8);
        Map<ProductRepository, Integer> productCountMap = basket.getProductCountMap();
        Assert.assertEquals(3, productCountMap.size());
        basket.clearProductMap();
        Assert.assertEquals(0, productCountMap.size());
    }

    @Test
    public void shouldCalculateWithoutDiscount() {
        basket.addProduct(MILK, 5);
        BigDecimal price = basket.calculateTotalPrice();
        Assert.assertEquals(MILK.getPrice().multiply(BigDecimal.valueOf(5)), price);
    }
}