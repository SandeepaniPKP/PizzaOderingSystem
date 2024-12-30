package patterns;

import models.Order;

/**
 * DeliveredState represents the final state of an order.
 */
public class DeliveredState implements OrderState {
    @Override
    public void next(OrderContext context, Order order) {
        System.out.println("Order #" + order.getOrderId() + " has already been delivered.");
    }
    @Override
    public void prev(OrderContext context, Order order) {
        context.setState(new OutForDeliveryState());
        notifyCustomer(order);
    }
    @Override
    public String getStatus() {
        return "Delivered";
    }
    @Override
    public void notifyCustomer(Order order) {
        System.out.println("Notification: Your order #" + order.getOrderId() + " has been delivered.");
    }
}
