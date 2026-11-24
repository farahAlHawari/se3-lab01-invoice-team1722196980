package hw2.decorator;

import hw2.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Decorator pattern - Concrete Component.
 * The original order service with in-memory storage. It knows nothing about
 * logging or caching and is NOT modified to support them.
 */
public class OrderService implements IOrderService {
    private final Map<Integer, Order> storage = new HashMap<>();
    private int nextId = 1;

    @Override
    public Order createOrder(double amount) {
        Order order = new Order(nextId++, amount);
        storage.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<Order> findOrder(int id) {
        // simulates a slow lookup in the data source
        System.out.println("   [Storage] Reading order #" + id + " from storage (slow)...");
        return Optional.ofNullable(storage.get(id));
    }
}
