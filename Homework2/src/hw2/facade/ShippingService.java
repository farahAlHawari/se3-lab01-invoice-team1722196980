package hw2.facade;

import hw2.model.Order;

/** Facade pattern - Subsystem class. */
public class ShippingService {
    public void createShipment(Order order) {
        System.out.println("   [Shipping] Shipment created for order #" + order.getId());
    }
}
