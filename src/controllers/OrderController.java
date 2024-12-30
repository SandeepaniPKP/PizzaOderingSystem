package controllers;

import models.Order;
import models.Pizza;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order != null && order.getOrderId() == orderId) { // Add null check
                return order;
            }
        }
        return null; // Return null if no matching order is found
    }

    public List<Pizza> getHighlyRatedCombinations() {
        List<Pizza> popularPizzas = new ArrayList<>();
        for (Order order : orders) {
            // Check if the order is null or rating is below 4
            if (order != null && order.getRating() >= 4) {
                popularPizzas.addAll(order.getPizzas());
            }
        }
        return popularPizzas;
    }
}
