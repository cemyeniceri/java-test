package io.unicraft.exercises.client.menu;

import java.util.HashMap;
import java.util.Map;

public class BasketMenuConstants {

    private BasketMenuConstants() {
    }

    public static final int ADD_PRODUCT_TO_BASKET = 1;
    public static final int DISPLAY_BASKET = 2;
    public static final int DISPLAY_PRICE = 3;
    public static final int CLEAR_BASKET = 4;
    public static final int BACK_TO_MAIN_MENU = 5;
    public static Map<Integer, String> basketMenu;

    static {
        basketMenu = new HashMap<>();
        basketMenu.put(ADD_PRODUCT_TO_BASKET, "Add product to the basket.");
        basketMenu.put(DISPLAY_BASKET, "Display products of the basket.");
        basketMenu.put(DISPLAY_PRICE, "Display price of the basket.");
        basketMenu.put(CLEAR_BASKET, "Clear basket.");
        basketMenu.put(BACK_TO_MAIN_MENU, "Back to main menu.");
    }
}
