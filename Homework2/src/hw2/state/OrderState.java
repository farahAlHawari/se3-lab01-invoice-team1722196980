package hw2.state;

import hw2.model.Order;

/**
 * State pattern - State interface.
 * Every operation an order supports is declared here; each concrete state
 * decides whether the operation is allowed and which state comes next.
 */
public interface OrderState {
    void pay(Order order);
    void ship(Order order);
    void deliver(Order order);
    void cancel(Order order);

    String getName();
}
