package io.unicraft.exercises.client.menu;

import java.util.Map;
import java.util.Scanner;

public class MenuHelper {
    private static Scanner scanner = new Scanner(System.in);
    private static final int SELECTION_ERROR = -1;

    public static int executeMenu(Map<Integer, String> menuMap, String menuTitle) {
        try {
            System.out.println("----------------------- ### -----------------------");
            System.out.println("----------   " + menuTitle.toUpperCase() + "   ----------");
            System.out.println("Please Select an Item From Menu : ");
            for (Integer menuNo : menuMap.keySet()) {
                System.out.println(menuNo + " - " + menuMap.get(menuNo));
            }
            System.out.println("----------------------- ### -----------------------");
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return SELECTION_ERROR;
        }
    }

    public static int askForValue(String description) {
        try {
            System.out.println("----------------------- = -----------------------");
            System.out.println("-- " + description + " --");
            System.out.println("----------------------- = -----------------------");
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return SELECTION_ERROR;
        }
    }

    public static int askForConfirmation(String description) {
        try {
            System.out.println("----------------------- = -----------------------");
            System.out.println("-- " + description + " (ANSWER => 1: NO, 2: YES) --");
            System.out.println("----------------------- = -----------------------");
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return SELECTION_ERROR;
        }
    }
}
