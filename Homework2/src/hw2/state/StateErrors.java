package hw2.state;

import hw2.model.Order;

/** Shared helper so every rejected transition prints a clear, consistent message. */
final class StateErrors {
    private StateErrors() {}

    static void notAllowed(Order order, String action, String reason) {
        System.out.println("[ERROR] Cannot " + action + " order #" + order.getId()
                + " (state: " + order.getState().getName() + ") - " + reason);
    }
}
