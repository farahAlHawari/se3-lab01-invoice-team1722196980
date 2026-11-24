package hw2;

import hw2.adapter.ExternalPayPalLib;
import hw2.adapter.PayPalAdapter;
import hw2.decorator.CachingDecorator;
import hw2.decorator.IOrderService;
import hw2.decorator.LoggingDecorator;
import hw2.decorator.OrderService;
import hw2.facade.CheckoutFacade;
import hw2.model.Order;
import hw2.strategy.CardPayment;
import hw2.strategy.CashOnDelivery;
import hw2.strategy.PaymentStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Console menu that ties the five patterns together.
 *  - Decorator: the order service is wrapped with caching and logging.
 *  - Strategy + Adapter: payment methods are chosen from a registry (no if/else or switch).
 *  - State: pay / ship / deliver / cancel are delegated to the order's current state.
 *  - Facade: option 9 runs the whole checkout with a single call.
 */
public class Main {

    private record MenuItem(String label, Runnable action) {}

    private final Scanner in = new Scanner(System.in);

    // Decorator pattern: same IOrderService type, extra behaviour layered on top
    private final IOrderService orders =
            new LoggingDecorator(new CachingDecorator(new OrderService()));

    // Facade pattern: the only object the menu uses for the full checkout
    private final CheckoutFacade checkout = new CheckoutFacade();

    // Strategy registry: menu number -> how to build the strategy (adding a method = adding a line)
    private final Map<Integer, Supplier<PaymentStrategy>> paymentMethods = new LinkedHashMap<>();
    private final Map<Integer, MenuItem> menu = new LinkedHashMap<>();

    private Order current;
    private boolean running = true;

    private Main() {
        paymentMethods.put(1, CardPayment::new);
        paymentMethods.put(2, CashOnDelivery::new);
        paymentMethods.put(3, () -> new PayPalAdapter(new ExternalPayPalLib()));   // Adapter pattern

        menu.put(1, new MenuItem("Create order", this::createOrder));
        menu.put(2, new MenuItem("Select payment method", this::selectPaymentMethod));
        menu.put(3, new MenuItem("Pay", () -> withOrder(Order::pay)));
        menu.put(4, new MenuItem("Ship", () -> withOrder(Order::ship)));
        menu.put(5, new MenuItem("Deliver", () -> withOrder(Order::deliver)));
        menu.put(6, new MenuItem("Cancel", () -> withOrder(Order::cancel)));
        menu.put(7, new MenuItem("Show status", () -> withOrder(o -> { })));
        menu.put(8, new MenuItem("Exit", () -> running = false));
        menu.put(9, new MenuItem("Checkout (Facade: inventory -> payment -> shipping -> notification)",
                () -> withOrder(checkout::placeOrder)));
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        while (running) {
            printMenu();
            String line = prompt("Choose: ");
            if (line == null) {          // end of input
                break;
            }
            MenuItem item = menu.get(parseInt(line));
            if (item == null) {
                System.out.println("Invalid choice, please enter a number from the menu.");
                continue;
            }
            System.out.println();
            item.action().run();
        }
        System.out.println("Goodbye!");
    }

    // ---------------- menu actions ----------------

    private void createOrder() {
        Integer amount = parseInt(prompt("Order amount: "));
        if (amount == null || amount <= 0) {
            System.out.println("Please enter a positive whole number.");
            return;
        }
        current = orders.createOrder(amount);
        System.out.println("Created: " + current);
    }

    private void selectPaymentMethod() {
        Order order = askOrder();
        if (order == null) {
            return;
        }
        System.out.println("Payment methods:");
        paymentMethods.forEach((number, factory) ->
                System.out.println("  " + number + ") " + factory.get().getName()));

        Supplier<PaymentStrategy> factory = paymentMethods.get(parseInt(prompt("Method: ")));
        if (factory == null) {
            System.out.println("Unknown payment method.");
            return;
        }
        order.setPaymentStrategy(factory.get());      // Strategy: swapped at runtime
        System.out.println("Selected: " + order.getPaymentStrategy().getName() + " -> " + order);
    }

    /** Asks for an order, runs the operation on it, then prints the order (so "show status" is an empty operation). */
    private void withOrder(Consumer<Order> operation) {
        Order order = askOrder();
        if (order == null) {
            return;
        }
        operation.accept(order);
        System.out.println(order);
    }

    // ---------------- helpers ----------------

    private Order askOrder() {
        String hint = (current == null) ? "" : " (Enter = #" + current.getId() + ")";
        String line = prompt("Order id" + hint + ": ");
        Integer id = (line == null || line.isBlank())
                ? (current == null ? null : current.getId())
                : parseInt(line);
        if (id == null) {
            System.out.println("No order selected. Create an order first (option 1).");
            return null;
        }
        Optional<Order> found = orders.findOrder(id);
        if (found.isEmpty()) {
            System.out.println("No order with id " + id + ".");
            return null;
        }
        current = found.get();
        return current;
    }

    private void printMenu() {
        System.out.println("\n========== Order Management ==========");
        menu.forEach((number, item) -> System.out.println(" " + number + ") " + item.label()));
    }

    private String prompt(String text) {
        System.out.print(text);
        return in.hasNextLine() ? in.nextLine().trim() : null;
    }

    private Integer parseInt(String text) {
        try {
            return Integer.valueOf(text.trim());
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }
}
