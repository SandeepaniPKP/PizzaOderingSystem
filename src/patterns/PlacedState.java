package patterns;

import models.Order;

/**
 * PlacedState represents the initial state of an order.
 */
public class PlacedState implements OrderState {
    @Override
    public void next(OrderContext context, Order order) {
        context.setState(new PreparingState());
        notifyCustomer(order);
    }

    @Override
    public void prev(OrderContext context, Order order) {
        System.out.println("Order is already in the initial state.");
    }

    @Override
    public String getStatus() {
        return "Placed";
    }

    @Override
    public void notifyCustomer(Order order) {
        System.out.println("Notification: Your order #" + order.getOrderId() + " has been placed.");
    }
}
