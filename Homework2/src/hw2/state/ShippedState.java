package hw2.state;

import hw2.model.Order;

/** State pattern - Concrete State: order is on its way to the customer. */
public class ShippedState implements OrderState {

    @Override
    public void pay(Order order) {
        StateErrors.notAllowed(order, "pay", "the order is already paid.");
    }

    @Override
    public void ship(Order order) {
        StateErrors.notAllowed(order, "ship", "the order is already shipped.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("[OK] Order #" + order.getId() + " delivered.");
        order.setState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) {
        StateErrors.notAllowed(order, "cancel", "a shipped order can no longer be cancelled.");
    }

    @Override
    public String getName() {
        return "Shipped";
    }
}
