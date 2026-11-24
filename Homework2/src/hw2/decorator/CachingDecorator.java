package hw2.decorator;

import hw2.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** Decorator pattern - Concrete Decorator B: adds an in-memory cache to lookups. */
public class CachingDecorator extends OrderServiceDecorator {
    private final Map<Integer, Order> cache = new HashMap<>();

    public CachingDecorator(IOrderService wrapped) {
        super(wrapped);
    }

    @Override
    public Order createOrder(double amount) {
        Order order = super.createOrder(amount);
        cache.put(order.getId(), order);          // a new order is immediately cached
        return order;
    }

    @Override
    public Optional<Order> findOrder(int id) {
        Order cached = cache.get(id);
        if (cached != null) {
            System.out.println("   [Cache] HIT for order #" + id);
            return Optional.of(cached);
        }
        System.out.println("   [Cache] MISS for order #" + id);
        Optional<Order> result = super.findOrder(id);
        result.ifPresent(order -> cache.put(id, order));
        return result;
    }
}
