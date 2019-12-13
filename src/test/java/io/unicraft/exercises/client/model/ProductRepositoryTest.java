package io.unicraft.exercises.client.model;

import org.junit.Test;

import static io.unicraft.exercises.client.model.ProductRepository.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
}