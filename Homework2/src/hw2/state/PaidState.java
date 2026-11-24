package hw2.state;

import hw2.model.Order;

/** State pattern - Concrete State: payment received, ready to be shipped. */
public class PaidState implements OrderState {

    @Override
    public void pay(Order order) {
        StateErrors.notAllowed(order, "pay", "the order is already paid.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("[OK] Order #" + order.getId() + " shipped.");
        order.setState(new ShippedState());
    }

    @Override
    public void deliver(Order order) {
        StateErrors.notAllowed(order, "deliver", "the order has not been shipped.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("[OK] Order #" + order.getId() + " cancelled (refund required).");
        order.setState(new CancelledState());
    }

    @Override
    public String getName() {
        return "Paid";
    }
}
