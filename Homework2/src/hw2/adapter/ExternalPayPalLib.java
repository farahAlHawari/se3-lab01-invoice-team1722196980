package hw2.adapter;

/**
 * Adapter pattern - Adaptee.
 * Simulates a third-party PayPal library. Its interface is DIFFERENT from the
 * one our system expects (PaymentStrategy.pay(double)), and it must NOT be modified.
 */
public class ExternalPayPalLib {
    private static final double PAYPAL_FEE_RATE = 0.03;

    /**
     * @param value    the amount to transfer
     * @param currency ISO currency code, e.g. "USD"
     * @return the total charged by PayPal (value + 3% fee)
     */
    public double makeTransaction(double value, String currency) {
        double total = value * (1 + PAYPAL_FEE_RATE);
        System.out.printf("   [PayPal Lib] Transaction of %.2f %s completed (value %.2f + 3%% fee)%n",
                total, currency, value);
        return total;
    }
}
