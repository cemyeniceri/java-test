package io.unicraft.exercises.client;

import io.unicraft.exercises.client.menu.BasketMenuConstants;
import io.unicraft.exercises.client.menu.MainMenuConstants;
import io.unicraft.exercises.client.menu.MenuHelper;
import io.unicraft.exercises.client.menu.ProductMenuConstants;
import io.unicraft.exercises.client.model.ProductRepository;

import static io.unicraft.exercises.client.menu.MenuHelper.executeMenu;

public class App {

    public static Basket basket = null;

    public static void main(String[] args) {
        executeMainMenu();
    }

    private static void executeMainMenu() {
        while (true) {
            switch (executeMenu(MainMenuConstants.mainMenu, "Main Menu")) {
                case MainMenuConstants.CREATE_NEW_BASKET:
                    basket = new Basket();
                    executeBasketMenu();
                    break;
                case MainMenuConstants.CONTINUE_WITH_OLD_BASKET:
                    if (basket != null)
                        executeBasketMenu();
                    else
                        System.out.println("You do not have any basket! Please create one.");
                    break;
                case MainMenuConstants.EXIT_FROM_APP:
                    System.out.println("Thanks for using application.");
                    return;
                default:
                    System.out.println("Wrong selection! Please enter a correct selection.");
            }
        }
    }

    private static void executeBasketMenu() {
        while (true) {
            switch (executeMenu(BasketMenuConstants.basketMenu, "Basket Menu")) {
                case BasketMenuConstants.ADD_PRODUCT_TO_BASKET:
                    executeAddProductMenu();
                    break;
                case BasketMenuConstants.DISPLAY_BASKET:
                    System.out.println(basket.getProductCountMap());
                    break;
                case BasketMenuConstants.DISPLAY_PRICE:
                    // TODO : Implement calculation logic to display price
                    System.out.println("Price is not implemented yet");
                    break;
                case BasketMenuConstants.CLEAR_BASKET:
                    if (MenuHelper.askForConfirmation("Do you want to clear basket?") == 2) {
                        basket.clearProductMap();
                        System.out.println("Basket cleared.");
                    }
                    break;
                case BasketMenuConstants.BACK_TO_MAIN_MENU:
                    return;
                default:
                    System.out.println("Wrong selection! Please enter a correct selection.");
            }
        }
    }

    private static void executeAddProductMenu() {
        while (true) {
            int selectedMenuItem = executeMenu(ProductMenuConstants.productMenu, "Add Product");
            if (selectedMenuItem == ProductMenuConstants.BACK_TO_BASKET_MENU_NUMBER) {
                return;
            } else {
                ProductRepository product = ProductRepository.findById(selectedMenuItem);
                if (product != null) {
                    int count = MenuHelper.askForValue("Enter count for selected production: ");
                    if (count > 0) {
                        basket.addProduct(product, count);
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
