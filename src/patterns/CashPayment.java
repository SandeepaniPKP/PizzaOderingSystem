
package patterns;

/**
 * CashPayment represents a payment method using cash.
 */
public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " in cash.");
    }
}
