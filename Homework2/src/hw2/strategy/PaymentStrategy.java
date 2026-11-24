package hw2.strategy;

/**
 * Strategy pattern - Strategy interface.
 * Every payment method implements this single contract, so the rest of the
 * code never needs to know which concrete method is being used.
 */
public interface PaymentStrategy {

    /**
     * Charges the customer.
     *
     * @param amount the order amount
     * @return the total actually charged (order amount + method-specific fees)
     */
    double pay(double amount);

    /** Display name used in messages and in the menu. */
    String getName();
}
