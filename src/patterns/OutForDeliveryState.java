package patterns;

import models.Order;

/**
 * OutForDeliveryState represents the state of an order out for delivery.
 */
public class OutForDeliveryState implements OrderState {
    @Override
    public void next(OrderContext context, Order order) {
        context.setState(new DeliveredState());
        notifyCustomer(order);
    }

    @Override
    public void prev(OrderContext context, Order order) {
        context.setState(new PreparingState());
        notifyCustomer(order);
    }

    @Override
    public String getStatus() {
        return "Out for Delivery";
    }

    @Override
    public void notifyCustomer(Order order) {
        System.out.println("Notification: Your order #" + order.getOrderId() + " is out for delivery.");
    }
}
