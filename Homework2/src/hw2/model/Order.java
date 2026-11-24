package hw2.model;

import hw2.state.OrderState;
import hw2.state.PendingState;
import hw2.strategy.PaymentStrategy;

/**
 * Context for two patterns:
 *  - State:    holds the current state and delegates every operation to it.
 *  - Strategy: holds the current payment strategy, replaceable at runtime.
 * There is no if/else on the status or on the payment method in this class.
 */
public class Order {
    private final int id;
    private final double amount;
    private OrderState state;
    private PaymentStrategy paymentStrategy;   // Strategy: null until the customer chooses
    private double amountCharged;              // what the chosen strategy actually charged

    public Order(int id, double amount) {
        this.id = id;
        this.amount = amount;
        this.state = new PendingState();       // every order starts as Pending
    }

    // ---- operations delegated to the current state ----
    public void pay()     { state.pay(this); }
    public void ship()    { state.ship(this); }
    public void deliver() { state.deliver(this); }
    public void cancel()  { state.cancel(this); }

    // ---- state management (called by the concrete states) ----
    public void setState(OrderState state) { this.state = state; }
    public OrderState getState()           { return state; }

    // ---- strategy management ----
    public void setPaymentStrategy(PaymentStrategy strategy) { this.paymentStrategy = strategy; }
    public PaymentStrategy getPaymentStrategy()              { return paymentStrategy; }

    public void setAmountCharged(double amountCharged) { this.amountCharged = amountCharged; }
    public double getAmountCharged()                   { return amountCharged; }

    public int getId()        { return id; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        String method = (paymentStrategy == null) ? "not selected" : paymentStrategy.getName();
        return "Order #" + id + " | amount: " + String.format("%.2f", amount)
                + " | payment: " + method + " | state: " + state.getName();
    }
}
