package patterns;

import models.Pizza;
import java.util.List;

public abstract class PizzaDecorator extends Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getCrust() {
        return pizza.getCrust();
    }

    @Override
    public void setCrust(String crust) {
        pizza.setCrust(crust);
    }

    @Override
    public String getSauce() {
        return pizza.getSauce();
    }

    @Override
    public void setSauce(String sauce) {
        pizza.setSauce(sauce);
    }

    @Override
    public List<String> getToppings() {
        return pizza.getToppings();
    }

    @Override
    public void setToppings(List<String> toppings) {
        pizza.setToppings(toppings);
    }

    @Override
    public String getSize() {
        return pizza.getSize();
    }

    @Override
    public void setSize(String size) {
        pizza.setSize(size);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }
}
