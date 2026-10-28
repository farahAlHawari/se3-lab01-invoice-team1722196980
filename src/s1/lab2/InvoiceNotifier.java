package s1.lab2;

public interface InvoiceNotifier {

    String send(String to, String subject, String body);
}