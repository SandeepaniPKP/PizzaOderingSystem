package patterns;

import java.util.List;

/**
 * Discount strategy for large pizzas.
 */
public class LargePizzaDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double basePrice, String size, List<String> toppings) {
        if (size.equalsIgnoreCase("Large")) {
            return basePrice * 0.30; // 30% discount for large pizzas
        }
        return 0.0;
    }
}
