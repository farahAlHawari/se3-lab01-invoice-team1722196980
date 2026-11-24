package hw2.decorator;

import hw2.model.Order;

import java.util.Optional;

/** Decorator pattern - Component interface: the order service contract. */
public interface IOrderService {
    Order createOrder(double amount);

    Optional<Order> findOrder(int id);
}
