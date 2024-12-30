package patterns;

import models.Pizza;
import java.util.Scanner;

public class ToppingsHandler extends CustomizationHandler {
    @Override
    public void handle(Pizza pizza) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Add a topping (Cheese, Pepperoni, Mushrooms, etc.):");
            pizza.getToppings().add(scanner.nextLine());

            System.out.println("Do you want to add another topping? (yes/no):");
            if (scanner.nextLine().equalsIgnoreCase("no")) {
                break;
            }
        }

        if (nextHandler != null) {
            nextHandler.handle(pizza);
        }
    }
}
