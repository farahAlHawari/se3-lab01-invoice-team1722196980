package s1.lab2;

public class InvoiceCalculator {

    public double total(Invoice invoice) {

        double subtotal = 0;

        for (InvoiceItem item : invoice.getItems()) {
            subtotal += item.getPrice() * item.getQty();
        }

        if (subtotal > 1000) {
            subtotal -= subtotal * 0.05;
        }

        return subtotal + subtotal * 0.15;
    }
}