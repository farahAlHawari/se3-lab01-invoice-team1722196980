package hw2.strategy;

/** Strategy pattern - Concrete Strategy: bank card, 2% processing fee. */
public class CardPayment implements PaymentStrategy {
    private static final double FEE_RATE = 0.02;

    @Override
    public double pay(double amount) {
        double total = amount * (1 + FEE_RATE);
        System.out.printf("   [Card] Charged %.2f (amount %.2f + 2%% card fee)%n", total, amount);
        return total;
    }

    @Override
    public String getName() {
        return "Bank Card";
    }
}
