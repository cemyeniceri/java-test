package io.unicraft.exercises;

import io.unicraft.exercises.client.MenuHelper;
import io.unicraft.exercises.client.constants.BasketMenuConstant;
import io.unicraft.exercises.client.constants.MainMenuConstant;
import io.unicraft.exercises.client.constants.ProductMenuConstant;
import io.unicraft.exercises.models.ProductRepository;
import io.unicraft.exercises.services.BasketService;

import java.time.LocalDate;

import static io.unicraft.exercises.client.MenuHelper.executeMenu;

public class App {

    public static BasketService basketService = null;

    public static void main(String[] args) {
        executeMainMenu();
    }

    private static void executeMainMenu() {
        while (true) {
            switch (executeMenu(MainMenuConstant.mainMenu, "Main Menu")) {
                case MainMenuConstant.CREATE_NEW_BASKET:
                    executeBasketMenuWithNewBasket();
                    break;
                case MainMenuConstant.CONTINUE_WITH_OLD_BASKET:
                    executeBasketMenu();
                    break;
                case MainMenuConstant.EXIT_FROM_APP:
                    System.out.println("Thanks for using application.");
                    return;
                default:
                    System.out.println("Wrong selection! Please enter a correct selection.");
            }
        }
    }

    private static void executeBasketMenuWithNewBasket() {
        LocalDate shoppingDate = MenuHelper.askForDate("Enter shopping date");
        if (shoppingDate != null) {
            basketService = new BasketService();
            basketService.setShoppingDate(shoppingDate);
            executeBasketMenu();
        }
    }

    private static void executeBasketMenu() {
        if (basketService == null) {
            System.out.println("You do not have any basket! Please create one.");
            return;
        }
        while (true) {
            switch (executeMenu(BasketMenuConstant.basketMenu, "Basket Menu")) {
                case BasketMenuConstant.ADD_PRODUCT_TO_BASKET:
                    executeAddProductMenu();
                    break;
                case BasketMenuConstant.DISPLAY_BASKET:
                    basketService.printDetails();
                    break;
                case BasketMenuConstant.DISPLAY_PRICE:
                    basketService.calculateTotalPrice().printSlip();
                    break;
                case BasketMenuConstant.CLEAR_BASKET:
                    if (MenuHelper.askForConfirmation("Do you want to clear basket?") == 2) {
                        basketService.clearProductMap();
                        System.out.println("Basket cleared.");
                    }
                    break;
                case BasketMenuConstant.BACK_TO_MAIN_MENU:
                    return;
                default:
                    System.out.println("Wrong selection! Please enter a correct selection.");
            }
        }
    }

    private static void executeAddProductMenu() {
        while (true) {
            int selectedMenuItem = executeMenu(ProductMenuConstant.productMenu, "Add Product");
            if (selectedMenuItem == ProductMenuConstant.BACK_TO_BASKET_MENU_NUMBER) {
                return;
            } else {
                ProductRepository product = ProductRepository.findById(selectedMenuItem);
                if (product != null) {
                    int count = MenuHelper.askForValue("Enter count for selected production: ");
                    if (count > 0) {
                        basketService.addProduct(product, count);
                        System.out.println("Product added.");
                        return;
                    } else {
                        System.out.println("Wrong count! Please select product again and enter correct value");
                    }
                } else {
                    System.out.println("Wrong selection! Please enter a correct selection.");
                }
            }
        }
    }
}
