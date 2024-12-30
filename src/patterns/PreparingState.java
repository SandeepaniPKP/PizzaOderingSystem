package patterns;

import models.Order;

/**
 * PreparingState represents the state of an order being prepared.
 */
public class PreparingState implements OrderState {
    @Override
    public void next(OrderContext context, Order order) {
        context.setState(new OutForDeliveryState());
        notifyCustomer(order);
    }
    @Override
    public void prev(OrderContext context, Order order) {
        context.setState(new PlacedState());
        notifyCustomer(order);
    }
    @Override
    public String getStatus() {
        return "Preparing";
    }
    @Override
    public void notifyCustomer(Order order) {
        System.out.println("Notification: Your order #" + order.getOrderId() + " is being prepared.");
    }
}
