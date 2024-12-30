package patterns;

/**
 * Payment strategy for digital wallet payments.
 */
public class DigitalWalletPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Digital Wallet.");
    }
}
