package io.unicraft.exercises.services;

import io.unicraft.exercises.models.ProductRepository;
import io.unicraft.exercises.models.SalesSlip;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

import static io.unicraft.exercises.models.ProductRepository.*;
import static org.junit.Assert.assertEquals;

public class BasketServiceTest {

    private BasketService basketService;

    @Before
    public void setUp() {
        basketService = new BasketService();
    }

    @Test
    public void addOneProductToBasketTest() {
        Map<ProductRepository, Integer> productCountMap = basketService.getProductCountMap();
        assertEquals(0, productCountMap.size());
        basketService.addProduct(SOUP, 3);
        assertEquals(1, productCountMap.size());
        assertEquals(3, productCountMap.get(SOUP).intValue());
    }

    @Test
    public void addMoreThanOneProductToBasketTest() {
        Map<ProductRepository, Integer> productCountMap = basketService.getProductCountMap();
        assertEquals(0, productCountMap.size());
        basketService.addProduct(SOUP, 3);
        assertEquals(1, productCountMap.size());
        assertEquals(3, productCountMap.get(SOUP).intValue());
        basketService.addProduct(SOUP, 3);
        assertEquals(1, productCountMap.size());
        assertEquals(6, productCountMap.get(SOUP).intValue());
    }

    @Test
    public void shouldClearProductCountMap() {
        basketService.addProduct(SOUP, 3);
        basketService.addProduct(MILK, 6);
        basketService.addProduct(BREAD, 8);
        Map<ProductRepository, Integer> productCountMap = basketService.getProductCountMap();
        assertEquals(3, productCountMap.size());
        basketService.clearProductMap();
        assertEquals(0, productCountMap.size());
    }

    @Test
    public void shouldCalculateWithoutDiscountWhenMilksSelected() {
        basketService.addProduct(MILK, 5);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(6.50).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateWithoutDiscountWhenSoupSelected() {
        basketService.addProduct(SOUP, 5);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(3.25).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateApplesWithoutDiscountWhenShoppingDateEarly() {
        basketService.setShoppingDate(LocalDate.now());
        basketService.addProduct(APPLE, 3);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(0.30).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateApplesWithoutDiscountWhenShoppingDateLate() {
        basketService.setShoppingDate(LocalDate.now().plusMonths(2));
        basketService.addProduct(APPLE, 3);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(0.30).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateApplesWithDiscountWhenShoppingDateAtStartDate() {
        basketService.setShoppingDate(LocalDate.now().plusDays(3));
        basketService.addProduct(APPLE, 3);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(0.27).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateApplesWithDiscountWhenShoppingDateAtEndDate() {
        basketService.setShoppingDate(LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
        basketService.addProduct(APPLE, 3);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(0.27).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateBreadWithoutDiscountWhenShoppingDateEarly() {
        basketService.setShoppingDate(LocalDate.now().minusDays(2));
        basketService.addProduct(BREAD, 3);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(2.40).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateBreadWithoutDiscountWhenShoppingDateLate() {
        basketService.setShoppingDate(LocalDate.now().plusDays(7));
        basketService.addProduct(BREAD, 3);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(2.40).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateBreadWithoutDiscountWhenBasketNotHasEnoughSoup() {
        basketService.setShoppingDate(LocalDate.now());
        basketService.addProduct(BREAD, 3);
        basketService.addProduct(SOUP, 1);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(3.05).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateBreadWithDiscountWhenShoppingDateAtStartDateAndBasketHasMoreThanOneSoup() {
        basketService.setShoppingDate(LocalDate.now().minusDays(1));
        basketService.addProduct(BREAD, 1);
        basketService.addProduct(SOUP, 3);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(2.35).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateBreadWithDiscountWhenShoppingDateAtEndDateAndBasketHasMoreThanOneSoup() {
        basketService.setShoppingDate(LocalDate.now().plusDays(6));
        basketService.addProduct(BREAD, 1);
        basketService.addProduct(SOUP, 3);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(2.35).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateBreadWithDiscountWhenShoppingDateValidAndSoupTwoTimesMoreThanBread() {
        basketService.setShoppingDate(LocalDate.now());
        basketService.addProduct(BREAD, 2);
        basketService.addProduct(SOUP, 10);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(7.30).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }

    @Test
    public void shouldCalculateWholeBasketWithDiscount() {
        basketService.setShoppingDate(LocalDate.now().plusDays(4));
        basketService.addProduct(BREAD, 2);
        basketService.addProduct(SOUP, 10);
        basketService.addProduct(MILK, 5);
        basketService.addProduct(APPLE, 7);
        SalesSlip slip = basketService.calculateTotalPrice();
        assertEquals(BigDecimal.valueOf(14.43).setScale(2, RoundingMode.CEILING), slip.getTotalPrice());
    }
}