package s1.lab2;

public interface InvoiceRepository {

    void save(Invoice invoice, double total);
}