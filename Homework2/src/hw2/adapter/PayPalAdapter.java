package hw2.adapter;

import hw2.strategy.PaymentStrategy;

/**
 * Adapter pattern - Adapter.
 * Implements the Target interface (PaymentStrategy) and translates
 * pay(amount) into the external library's makeTransaction(value, currency).
 * Because it is a PaymentStrategy, PayPal becomes the third payment strategy and
 * the rest of the code uses it exactly like CardPayment or CashOnDelivery.
 */
public class PayPalAdapter implements PaymentStrategy {
    private static final String DEFAULT_CURRENCY = "USD";

    private final ExternalPayPalLib payPalLib;   // Aggregation: the adaptee
    private final String currency;

    public PayPalAdapter(ExternalPayPalLib payPalLib) {
        this(payPalLib, DEFAULT_CURRENCY);
    }

    public PayPalAdapter(ExternalPayPalLib payPalLib, String currency) {
        this.payPalLib = payPalLib;
        this.currency = currency;
    }

    @Override
    public double pay(double amount) {
        // the interface conversion: pay(amount) -> makeTransaction(value, currency)
        return payPalLib.makeTransaction(amount, currency);
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}
