package patterns;

import java.util.List;

/**
 * Strategy Pattern interface for applying discounts.
 */
public interface DiscountStrategy {
    double calculateDiscount(double basePrice, String size, List<String> toppings);
}
