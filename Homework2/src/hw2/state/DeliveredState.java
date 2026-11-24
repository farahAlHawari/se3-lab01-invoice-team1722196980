package hw2.state;

import hw2.model.Order;

/** State pattern - Concrete State (final): order reached the customer. */
public class DeliveredState implements OrderState {

    @Override
    public void pay(Order order) {
        StateErrors.notAllowed(order, "pay", "the order is already completed.");
    }

    @Override
    public void ship(Order order) {
        StateErrors.notAllowed(order, "ship", "the order is already delivered.");
    }

    @Override
    public void deliver(Order order) {
        StateErrors.notAllowed(order, "deliver", "the order is already delivered.");
    }

    @Override
    public void cancel(Order order) {
        StateErrors.notAllowed(order, "cancel", "a delivered order cannot be cancelled.");
    }

    @Override
    public String getName() {
        return "Delivered";
    }
}
