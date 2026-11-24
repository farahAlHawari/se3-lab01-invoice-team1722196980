package hw2.facade;

import hw2.model.Order;

/**
 * Facade pattern - Facade.
 * One entry point (placeOrder) hides the coordination of four subsystems:
 * inventory -> payment (via the chosen PaymentStrategy) -> shipping -> notification.
 * The client never touches the services directly.
 */
public class CheckoutFacade {
    private static final int DEFAULT_STOCK = 3;

    private final InventoryService inventory;
    private final ShippingService shipping;
    private final NotificationService notification;

    public CheckoutFacade() {
        this(DEFAULT_STOCK);
    }

    public CheckoutFacade(int initialStock) {
        this.inventory = new InventoryService(initialStock);
        this.shipping = new ShippingService();
        this.notification = new NotificationService();
    }

    /** @return true if the whole checkout succeeded, false if any step failed (the flow stops there). */
    public boolean placeOrder(Order order) {
        System.out.println("--- Checkout started for order #" + order.getId() + " ---");

        // 1) inventory
        if (!inventory.reserve(order)) {
            notification.notifyCustomer(order, "Sorry, some items are out of stock.");
            return false;
        }

        // 2) payment: State + Strategy decide whether it is allowed and how it is charged
        if (!advance(order, order::pay)) {
            inventory.release(order);              // roll back the reservation
            notification.notifyCustomer(order, "Payment failed, order not placed.");
            return false;
        }

        // 3) shipping
        shipping.createShipment(order);
        if (!advance(order, order::ship)) {
            return false;
        }

        // 4) notification
        notification.notifyCustomer(order, "Your order is paid and shipped. Thank you!");
        System.out.println("--- Checkout finished: " + order + " ---");
        return true;
    }

    /**
     * Runs a state operation and reports whether the order really moved to a new state.
     * Every valid transition replaces the state object, so an unchanged state means it was rejected.
     */
    private boolean advance(Order order, Runnable operation) {
        Object before = order.getState();
        operation.run();
        return order.getState() != before;
    }
}
