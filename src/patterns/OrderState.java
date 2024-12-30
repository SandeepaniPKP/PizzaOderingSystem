package patterns;

import models.Order;

/**
 * OrderState defines the interface for the State Pattern.
 */
public interface OrderState {
    void next(OrderContext context, Order order);
    void prev(OrderContext context, Order order);
    String getStatus();
    void notifyCustomer(Order order);
}
