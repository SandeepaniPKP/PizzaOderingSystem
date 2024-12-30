package patterns;

import controllers.OrderController;
import models.Order;


public class PlaceOrderCommand implements Command {
    private OrderController orderController;
    private Order order;

    public PlaceOrderCommand(OrderController orderController, Order order) {
        this.orderController = orderController;
        this.order = order;
    }

    @Override
    public void execute() {
        orderController.addOrder(order);
        System.out.println("Order placed successfully: " + order);
    }
}

