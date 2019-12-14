package io.unicraft.exercises.models;

import io.unicraft.exercises.discounters.AppleDiscounter;
import io.unicraft.exercises.discounters.BreadDiscounter;
import io.unicraft.exercises.discounters.InactiveDiscounter;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static io.unicraft.exercises.models.ProductRepository.*;
import static org.junit.Assert.*;

public class ProductRepositoryTest {

    @Test
    public void checkProductRepositorySize() {
        assertEquals(4, ProductRepository.values().length);
    }

    @Test
    public void findById() {
        assertNull(ProductRepository.findById(5));
        assertEquals(SOUP, ProductRepository.findById(1));
        assertEquals(BREAD, ProductRepository.findById(2));
        assertEquals(MILK, ProductRepository.findById(3));
        assertEquals(APPLE, ProductRepository.findById(4));
    }

    @Test
    public void shouldCheckSoupProperties() {
        assertEquals(1, SOUP.getId());
        assertEquals(BigDecimal.valueOf(0.65).setScale(2, RoundingMode.CEILING), SOUP.getPrice());
        assertEquals("Soup", SOUP.getName());
        assertEquals("tin", SOUP.getUnit());
        assertTrue(SOUP.getDiscounter() instanceof InactiveDiscounter);
    }

    @Test
    public void shouldCheckBreadProperties() {
        assertEquals(2, BREAD.getId());
        assertEquals(BigDecimal.valueOf(0.80).setScale(2, RoundingMode.CEILING), BREAD.getPrice());
        assertEquals("Bread", BREAD.getName());
        assertEquals("loaf", BREAD.getUnit());
        assertTrue(BREAD.getDiscounter() instanceof BreadDiscounter);
    }

    @Test
    public void shouldCheckMilkProperties() {
        assertEquals(3, MILK.getId());
        assertEquals(BigDecimal.valueOf(1.30).setScale(2, RoundingMode.CEILING), MILK.getPrice());
        assertEquals("Milk", MILK.getName());
        assertEquals("bottle", MILK.getUnit());
        assertTrue(MILK.getDiscounter() instanceof InactiveDiscounter);
    }

    @Test
    public void shouldCheckAppleProperties() {
        assertEquals(4, APPLE.getId());
        assertEquals(BigDecimal.valueOf(0.10).setScale(2, RoundingMode.CEILING), APPLE.getPrice());
        assertEquals("Apple", APPLE.getName());
        assertEquals("single", APPLE.getUnit());
        assertTrue(APPLE.getDiscounter() instanceof AppleDiscounter);
    }
}