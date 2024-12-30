package patterns;

import models.Pizza;
import java.util.Scanner;

public class CrustHandler extends CustomizationHandler {
    @Override
    public void handle(Pizza pizza) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose crust (Thin, Thick, Stuffed):");
        pizza.setCrust(scanner.nextLine());

        if (nextHandler != null) {
            nextHandler.handle(pizza);
        }
    }


}
