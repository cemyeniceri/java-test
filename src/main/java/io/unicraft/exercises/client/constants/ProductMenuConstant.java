package io.unicraft.exercises.client.constants;

import io.unicraft.exercises.models.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class ProductMenuConstant {

    private ProductMenuConstant() {
    }

    public static final int BACK_TO_BASKET_MENU_NUMBER = ProductRepository.values().length + 1;
    public static Map<Integer, String> productMenu;

    static {
        productMenu = new HashMap<>();
        for (ProductRepository product : ProductRepository.values()) {
            productMenu.put(
                    product.getId(),
                    String.format("%s (Unit: %s, price: %.2f)", product.getName(), product.getUnit(), product.getPrice())
            );
        }
        productMenu.put(BACK_TO_BASKET_MENU_NUMBER, "Back to Basket Menu.");
    }
}
