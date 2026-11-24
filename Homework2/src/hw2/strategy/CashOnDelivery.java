package hw2.strategy;

/** Strategy pattern - Concrete Strategy: cash paid on delivery, flat 5.00 service fee. */
public class CashOnDelivery implements PaymentStrategy {
    private static final double SERVICE_FEE = 5.00;

    @Override
    public double pay(double amount) {
        double total = amount + SERVICE_FEE;
        System.out.printf("   [Cash on Delivery] Will collect %.2f (amount %.2f + 5.00 service fee)%n",
                total, amount);
        return total;
    }

    @Override
    public String getName() {
        return "Cash on Delivery";
    }
}
