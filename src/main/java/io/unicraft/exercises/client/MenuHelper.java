package io.unicraft.exercises.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class MenuHelper {
    private static Scanner scanner = new Scanner(System.in);
    private static final int SELECTION_ERROR = -1;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static int executeMenu(Map<Integer, String> menuMap, String menuTitle) {
        try {
            printHorizontalLine();
            System.out.println("----------   " + menuTitle.toUpperCase() + "   ----------");
            System.out.println("Please Select an Item From Menu : ");
            for (Integer menuNo : menuMap.keySet()) {
                System.out.println(menuNo + " - " + menuMap.get(menuNo));
            }
            printHorizontalLine();
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return SELECTION_ERROR;
        }
    }

    public static int askForValue(String description) {
        try {
            printHorizontalLine();
            System.out.println("-- " + description + " --");
            int value = Integer.parseInt(scanner.nextLine());
            printHorizontalLine();
            return value;
        } catch (Exception e) {
            return SELECTION_ERROR;
        }
    }

    public static int askForConfirmation(String description) {
        try {
            printHorizontalLine();
            System.out.println("-- " + description + " (ANSWER => 1: NO, 2: YES) --");
            int answer = Integer.parseInt(scanner.nextLine());
            printHorizontalLine();
            return answer;
        } catch (Exception e) {
            return SELECTION_ERROR;
        }
    }

    public static LocalDate askForDate(String description) {
        printHorizontalLine();
        System.out.println("-- " + description + " (Format: yyyyMMdd) : --");
        String date = scanner.nextLine();
        try {
            LocalDate formattedDate = LocalDate.parse(date, formatter);
            System.out.println("Entered date is : " + formattedDate.toString());
            printHorizontalLine();
            return formattedDate;
        } catch (Exception ex) {
            System.out.println("Entered date format is not valid!");
            return null;
        }
    }

    private static void printHorizontalLine() {
        System.out.println("----------------------- ### -----------------------");
    }
}
