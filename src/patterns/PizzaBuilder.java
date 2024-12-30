package patterns;

import models.Pizza;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder Pattern to construct customizable Pizza objects.
 */
public class PizzaBuilder {
    private String crust;
    private String sauce;
    private final List<String> toppings = new ArrayList<>();
    private String size;

    public PizzaBuilder setCrust(String crust) {
        this.crust = crust;
        return this;
    }
    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }
    public PizzaBuilder addTopping(String topping) {
        this.toppings.add(topping);
        return this;
    }
    public PizzaBuilder setToppings(List<String> toppings) {
        this.toppings.clear();
        this.toppings.addAll(toppings);
        return this;
    }
    public PizzaBuilder setSize(String size) {
        this.size = size;
        return this;
    }
    public Pizza build() {
        return new Pizza();
    }
}
