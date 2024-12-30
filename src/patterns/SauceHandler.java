package patterns;

import models.Pizza;
import java.util.Scanner;

public class SauceHandler extends CustomizationHandler {
    @Override
    public void handle(Pizza pizza) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose sauce (Tomato, Barbecue, Pesto):");
        pizza.setSauce(scanner.nextLine());

        if (nextHandler != null) {
            nextHandler.handle(pizza);
        }
    }

}
