package s1.lab2;

import java.util.Locale;

public class InvoiceFormatter {

    public String format(Invoice invoice, double total) {

        StringBuilder sb = new StringBuilder("INVOICE ")
                .append(invoice.getNumber())
                .append("\n");

        for (InvoiceItem item : invoice.getItems()) {

            sb.append(item.getName())
                    .append(" x")
                    .append(item.getQty())
                    .append(" = ")
                    .append(String.format(
                            Locale.ROOT,
                            "%.2f",
                            item.getPrice() * item.getQty()
                    ))
                    .append("\n");
        }

        return sb.append("TOTAL: ")
                .append(String.format(Locale.ROOT, "%.2f", total))
                .toString();
    }
}