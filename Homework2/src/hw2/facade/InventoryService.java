package hw2.facade;

import hw2.model.Order;

/**
 * Facade pattern - Subsystem class.
 * In-memory stock simulation: a few units are available, each checkout reserves one.
 */
public class InventoryService {
    private int stock;

    public InventoryService(int initialStock) {
        this.stock = initialStock;
    }

    public boolean reserve(Order order) {
        if (stock <= 0) {
            System.out.println("   [Inventory] Out of stock - cannot reserve items for order #" + order.getId());
            return false;
        }
        stock--;
        System.out.println("   [Inventory] Items reserved for order #" + order.getId() + " (remaining stock: " + stock + ")");
        return true;
    }

    public void release(Order order) {
        stock++;
        System.out.println("   [Inventory] Reservation released for order #" + order.getId() + " (remaining stock: " + stock + ")");
    }
}
