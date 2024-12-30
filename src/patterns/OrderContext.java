package patterns;

import models.Order;

/**
 * OrderContext manages the current state of an order using the State Pattern.
 */
public class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new PlacedState(); // Default state
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getStatus() {
        return state.getStatus();
    }

    public void next(Order order) {
        state.next(this, order);
    }

    public void prev(Order order) {
        state.prev(this, order);
    }

    public void notifyCustomer(Order order) {
        state.notifyCustomer(order);
    }
}
