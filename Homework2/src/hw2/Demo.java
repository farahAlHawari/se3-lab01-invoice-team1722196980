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

/** Non-interactive run of all scenarios, handy for clean screenshots. Run: hw2.Demo */
public class Demo {
    public static void main(String[] args) {
        IOrderService orders = new LoggingDecorator(new CachingDecorator(new OrderService()));

        Map<String, PaymentStrategy> methods = new LinkedHashMap<>();
        methods.put("Bank Card", new CardPayment());
        methods.put("Cash on Delivery", new CashOnDelivery());
        methods.put("PayPal (through Adapter)", new PayPalAdapter(new ExternalPayPalLib()));

        // Scenario 1: complete valid flow, once per payment method
        methods.forEach((name, strategy) -> {
            System.out.println("\n##### Scenario 1: full valid flow with " + name + " #####");
            Order order = orders.createOrder(200.0);
            order.setPaymentStrategy(strategy);
            order.pay();
            order.ship();
            order.deliver();
            System.out.println(order);
        });

        // Scenario 2: transitions that are not allowed (State)
        System.out.println("\n##### Scenario 2: forbidden transitions (State) #####");
        Order late = orders.createOrder(80.0);
        late.ship();                                  // Pending  -> ship
        late.setPaymentStrategy(new CardPayment());
        late.pay();
        late.pay();                                   // Paid     -> pay
        late.ship();
        late.deliver();
        late.ship();                                  // Delivered -> ship
        late.cancel();                                // Delivered -> cancel
        System.out.println(late);

        // Scenario 3: whole checkout with one call (Facade)
        System.out.println("\n##### Scenario 3: one-call checkout (Facade) #####");
        CheckoutFacade checkout = new CheckoutFacade();
        Order viaFacade = orders.createOrder(300.0);
        viaFacade.setPaymentStrategy(new PayPalAdapter(new ExternalPayPalLib()));
        checkout.placeOrder(viaFacade);
    }
}
