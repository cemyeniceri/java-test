package io.unicraft.exercises;

import io.unicraft.exercises.models.SalesSlip;
import io.unicraft.exercises.services.BasketService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static io.unicraft.exercises.models.ProductRepository.*;
import static org.junit.Assert.assertEquals;

/**
 * Customer Scenarios
 * <p>
 * scenario_1 :
 * Price a basket containing: 3 tins of soup and 2 loaves of bread, bought today,
 * Expected total cost = 3.15;
 * scenario_2 :
 * Price a basket containing: 6 apples and a bottle of milk, bought today,
 * Expected total cost = 1.90;
 * scenario_3 :
 * Price a basket containing: 6 apples and a bottle of milk, bought in 5 days time,
 * Expected total cost = 1.84;
 * scenario_4 :
 * Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread, bought in 5 days time,
 * Expected total cost = 1.97.
 */
public class BasketServiceAcceptanceTest {

    private BasketService basketService;

    @Before
    public void setUp() {
        basketService = new BasketService();
    }

    @Test
    public void scenario_1() {
        basketService.setShoppingDate(LocalDate.now());
        basketService.addProduct(SOUP, 3);
        basketService.addProduct(BREAD, 2);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(3.15).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void scenario_2() {
        basketService.setShoppingDate(LocalDate.now());
        basketService.addProduct(APPLE, 6);
        basketService.addProduct(MILK, 1);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(1.90).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void scenario_3() {
        basketService.setShoppingDate(LocalDate.now().plusDays(5));
        basketService.addProduct(APPLE, 6);
        basketService.addProduct(MILK, 1);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(1.84).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void scenario_4() {
        basketService.setShoppingDate(LocalDate.now().plusDays(5));
        basketService.addProduct(APPLE, 3);
        basketService.addProduct(SOUP, 2);
        basketService.addProduct(BREAD, 1);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(1.97).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

}


