package s1.lab2;

public class EmailInvoiceNotifier implements InvoiceNotifier {

    @Override
    public String send(String to, String subject, String body) {

        String message = "To: " + to
                + "\nSubject: " + subject
                + "\n\n" + body;

        System.out.println(body);
        System.out.println("Sending email...");

        return message;
    }
}