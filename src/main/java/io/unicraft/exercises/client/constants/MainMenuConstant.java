package io.unicraft.exercises.client.constants;

import java.util.HashMap;
import java.util.Map;

public class MainMenuConstant {

    private MainMenuConstant() {
    }

    public static final int CREATE_NEW_BASKET = 1;
    public static final int CONTINUE_WITH_OLD_BASKET = 2;
    public static final int EXIT_FROM_APP = 3;
    public static Map<Integer, String> mainMenu;

    static {
        mainMenu = new HashMap<>();
        mainMenu.put(CREATE_NEW_BASKET, "Create a new basket");
        mainMenu.put(CONTINUE_WITH_OLD_BASKET, "Continue with your basket");
        mainMenu.put(EXIT_FROM_APP, "Exit from application.");
    }
}
