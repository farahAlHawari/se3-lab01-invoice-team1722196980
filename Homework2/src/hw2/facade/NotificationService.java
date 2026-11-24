package hw2.facade;

import hw2.model.Order;

/** Facade pattern - Subsystem class. */
public class NotificationService {
    public void notifyCustomer(Order order, String message) {
        System.out.println("   [Notification] Order #" + order.getId() + ": " + message);
    }
}
