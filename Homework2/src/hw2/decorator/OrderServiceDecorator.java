package hw2.decorator;

import hw2.model.Order;

import java.util.Optional;

/**
 * Decorator pattern - Base Decorator.
 * Implements the same interface as the component, wraps another IOrderService,
 * and forwards every call to it. Concrete decorators override only what they add.
 */
public abstract class OrderServiceDecorator implements IOrderService {
    protected final IOrderService wrapped;

    protected OrderServiceDecorator(IOrderService wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Order createOrder(double amount) {
        return wrapped.createOrder(amount);
    }

    @Override
    public Optional<Order> findOrder(int id) {
        return wrapped.findOrder(id);
    }
}
