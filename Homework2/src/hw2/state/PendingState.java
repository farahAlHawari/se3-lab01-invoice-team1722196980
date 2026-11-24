package hw2.state;

import hw2.model.Order;
import hw2.strategy.PaymentStrategy;

/** State pattern - Concrete State: order created, waiting for payment. */
public class PendingState implements OrderState {

    @Override
    public void pay(Order order) {
        PaymentStrategy strategy = order.getPaymentStrategy();
        if (strategy == null) {   // a missing choice, not a choice between methods
            StateErrors.notAllowed(order, "pay", "no payment method selected.");
            return;
        }
        // Strategy pattern: the state delegates the payment to whatever strategy is set.
        double charged = strategy.pay(order.getAmount());
        order.setAmountCharged(charged);
        System.out.println("[OK] Order #" + order.getId() + " paid via " + strategy.getName() + ".");
        order.setState(new PaidState());
    }

    @Override
    public void ship(Order order) {
        StateErrors.notAllowed(order, "ship", "payment is required first.");
    }

    @Override
    public void deliver(Order order) {
        StateErrors.notAllowed(order, "deliver", "the order has not been shipped.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("[OK] Order #" + order.getId() + " cancelled.");
        order.setState(new CancelledState());
    }

    @Override
    public String getName() {
        return "Pending";
    }
}
