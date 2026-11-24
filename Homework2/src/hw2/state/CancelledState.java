package hw2.state;

import hw2.model.Order;

/** State pattern - Concrete State (final): order was cancelled. */
public class CancelledState implements OrderState {

    @Override
    public void pay(Order order) {
        StateErrors.notAllowed(order, "pay", "the order is cancelled.");
    }

    @Override
    public void ship(Order order) {
        StateErrors.notAllowed(order, "ship", "the order is cancelled.");
    }

    @Override
    public void deliver(Order order) {
        StateErrors.notAllowed(order, "deliver", "the order is cancelled.");
    }

    @Override
    public void cancel(Order order) {
        StateErrors.notAllowed(order, "cancel", "the order is already cancelled.");
    }

    @Override
    public String getName() {
        return "Cancelled";
    }
}
