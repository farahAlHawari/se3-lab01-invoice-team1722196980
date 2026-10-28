package s1.lab2;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInvoiceRepository implements InvoiceRepository {

    private final List<String> rows = new ArrayList<>();

    @Override
    public void save(Invoice invoice, double total) {

        rows.add(
                invoice.getNumber() + ";"
                        + invoice.getCustomerEmail() + ";"
                        + total
        );
    }

    public List<String> rows() {
        return rows;
    }
}