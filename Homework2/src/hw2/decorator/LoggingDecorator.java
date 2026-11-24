package hw2.decorator;

import hw2.model.Order;

import java.util.Optional;

/** Decorator pattern - Concrete Decorator A: adds logging around every call. */
public class LoggingDecorator extends OrderServiceDecorator {

    public LoggingDecorator(IOrderService wrapped) {
        super(wrapped);
    }

    @Override
    public Order createOrder(double amount) {
        System.out.printf("[LOG] createOrder(amount=%.2f)%n", amount);
        Order order = super.createOrder(amount);
        System.out.println("[LOG] createOrder -> created order #" + order.getId());
        return order;
    }

    @Override
    public Optional<Order> findOrder(int id) {
        System.out.println("[LOG] findOrder(id=" + id + ")");
        Optional<Order> result = super.findOrder(id);
        System.out.println("[LOG] findOrder -> " + (result.isPresent() ? "found" : "not found"));
        return result;
    }
}
