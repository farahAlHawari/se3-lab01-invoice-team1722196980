package s1.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Invoice {

    public static final List<String> DATABASE = new ArrayList<>();
    public static final List<String> FILES = new ArrayList<>();

    private final String number;
    private final String customerEmail;
    private final List<InvoiceItem> items = new ArrayList<>();

    public Invoice(String number, String customerEmail) {
        this.number = number;
        this.customerEmail = customerEmail;
    }

    public void addItem(String name, double price, int qty) {
        items.add(new InvoiceItem(name, price, qty));
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public double calculateTotal() {

        double subtotal = 0;

        for (InvoiceItem item : items) {
            subtotal += item.price * item.qty;
        }

        if (subtotal > 1000) {
            subtotal -= subtotal * 0.05;
        }

        return subtotal + subtotal * 0.15;
    }

    public String printInvoice() {

        StringBuilder sb = new StringBuilder();

        sb.append("INVOICE ")
                .append(number)
                .append("\n");

        for (InvoiceItem item : items) {

            double lineTotal = item.price * item.qty;

            sb.append(item.name)
                    .append(" x")
                    .append(item.qty)
                    .append(" = ")
                    .append(String.format(Locale.ROOT, "%.2f", lineTotal))
                    .append("\n");
        }

        sb.append("TOTAL: ")
                .append(String.format(Locale.ROOT, "%.2f", calculateTotal()));

        String text = sb.toString();

        System.out.println(text);

        return text;
    }

    public void saveToDatabase(String type) {

        String row = number + ";" + customerEmail + ";" + calculateTotal();

        if ("DATABASE".equals(type)) {
            DATABASE.add(row);
        } else {
            FILES.add(row);
        }
    }

    public String sendEmail() {

        String message = "To: " + customerEmail
                + "\nSubject: Invoice " + number
                + "\n\n" + printInvoice();

        System.out.println("Sending email...");

        return message;
    }

    public String sendwhatsapp() {

        String message = "To: sendwhatsapp"
                + "\nSubject: Invoice " + number
                + "\n\n" + printInvoice();

        System.out.println("Sending sendwhatsapp...");

        return message;
    }
}