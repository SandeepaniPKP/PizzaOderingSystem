package models;

import java.util.List;

public class Order {
    private int orderId;
    private String customerName;
    private List<Pizza> pizzas;
    private String status;
    private String orderType;
    private String feedback; // Feedback provided by the customer
    private int rating;      // Rating out of 5

    public Order(int orderId, String customerName, List<Pizza> pizzas, String status, String orderType) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.pizzas = pizzas;
        this.status = status;
        this.orderType = orderType;
        this.feedback = ""; // Default empty feedback
        this.rating = 0;    // Default no rating
    }

    public int getOrderId() {        return orderId;
    }
    public String getCustomerName() {        return customerName;
    }
    public List<Pizza> getPizzas() {        return pizzas;
    }
    public String getStatus() {        return status;
    }
    public String getOrderType() {        return orderType;
    }
    public String getFeedback() {        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public int getRating() {        return rating;
    }
    public void setRating(int rating) {        this.rating = rating;
    }
    public void setStatus(String paid) {        this.status = paid;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Customer: ").append(customerName).append("\n");
        sb.append("Order Type: ").append(orderType).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Pizzas:\n");
        for (Pizza pizza : pizzas) {
            sb.append("  - ").append(pizza.getDescription()).append("\n");
        }
        sb.append("Feedback: ").append(feedback == null ? "N/A" : feedback).append("\n");
        sb.append("Rating: ").append(rating == 0 ? "N/A" : rating).append("\n");
        return sb.toString();
    }
}

