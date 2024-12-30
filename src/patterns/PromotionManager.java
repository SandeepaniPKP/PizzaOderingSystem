package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages seasonal promotions by applying multiple discount strategies.
 */
public class PromotionManager {
    private List<DiscountStrategy> strategies = new ArrayList<>();

        public void addStrategy(DiscountStrategy strategy) {
        strategies.add(strategy);
    }

    public double calculateTotalDiscount(double basePrice, String size, List<String> toppings) {
        double maxDiscount = 0.0;
        for (DiscountStrategy strategy : strategies) {
            double discount = strategy.calculateDiscount(basePrice, size, toppings);
            maxDiscount = Math.max(maxDiscount, discount);
        }
        return maxDiscount;
    }
}
