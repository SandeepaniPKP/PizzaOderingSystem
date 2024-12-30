package patterns;

/**
 * Strategy Pattern interface for payment methods.
 */
public interface PaymentStrategy {
    void pay(double amount);
}
