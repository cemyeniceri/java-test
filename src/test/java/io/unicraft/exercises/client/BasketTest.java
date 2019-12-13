package io.unicraft.exercises.client;

import io.unicraft.exercises.client.model.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
    public void shouldCalculateWithoutDiscountWhenDiscountlessProductsSelected() {
        basket.addProduct(MILK, 5);
        BigDecimal price = basket.calculateTotalPrice();
        Assert.assertEquals(BigDecimal.valueOf(6.5), price);
    }

    @Test
    public void shouldCalculateApplesWithoutDiscountWhenShoppingDateEarly() {
        basket.setShoppingDate(LocalDate.now());
        basket.addProduct(APPLE, 3);
        BigDecimal price = basket.calculateTotalPrice();
        Assert.assertEquals(BigDecimal.valueOf(0.3), price);
    }

    @Test
    public void shouldCalculateApplesWithoutDiscountWhenShoppingDateLate() {
        basket.setShoppingDate(LocalDate.now().plusMonths(2));
        basket.addProduct(APPLE, 3);
        BigDecimal price = basket.calculateTotalPrice();
        Assert.assertEquals(BigDecimal.valueOf(0.3), price);
    }

    @Test
    public void shouldCalculateApplesWithDiscountWhenShoppingDateAtStartDate() {
        basket.setShoppingDate(LocalDate.now().plusDays(3));
        basket.addProduct(APPLE, 3);
        BigDecimal price = basket.calculateTotalPrice();
        Assert.assertEquals(BigDecimal.valueOf(0.27), price);
    }

    @Test
    public void shouldCalculateApplesWithDiscountWhenShoppingDateAtEndDate() {
        basket.setShoppingDate(LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
        basket.addProduct(APPLE, 3);
        BigDecimal price = basket.calculateTotalPrice();
        Assert.assertEquals(BigDecimal.valueOf(0.27), price);
    }
}