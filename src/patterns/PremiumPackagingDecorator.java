package patterns;

import models.Pizza;

public class PremiumPackagingDecorator extends PizzaDecorator {
    public PremiumPackagingDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Premium Packaging";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 100.0; // Add Rs.100 for premium packaging
    }
}
