package patterns;

import java.util.List;

/**
 * Discount strategy for pizzas with cheese toppings.
 */
public class CheeseToppingDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double basePrice, String size, List<String> toppings) {
        if (toppings != null && toppings.contains("Cheese")) {
            return basePrice * 0.25; // 25% discount for pizzas with cheese
        }
        return 0.0;
    }
}
