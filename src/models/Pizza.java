package models;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String crust;
    private String sauce;
    private List<String> toppings = new ArrayList<>();
    private String size;

    // Getters and Setters
    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return "Pizza [Crust=" + crust + ", Sauce=" + sauce + ", Toppings=" + String.join(", ", toppings) + ", Size=" + size + "]";
    }

    public double getPrice() {
        double price = 1000.0; // Base price
        if ("Medium".equalsIgnoreCase(size)) {
            price += 200.0;
        } else if ("Large".equalsIgnoreCase(size)) {
            price += 400.0;
        }
        return price;
    }
}

