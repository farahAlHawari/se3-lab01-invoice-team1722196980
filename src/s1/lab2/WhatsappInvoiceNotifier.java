package s1.lab2;

public class WhatsappInvoiceNotifier implements InvoiceNotifier {

    @Override
    public String send(String to, String subject, String body) {

        String message = "To: sendwhatsapp"
                + "\nSubject: " + subject
                + "\n\n" + body;

        System.out.println(body);
        System.out.println("Sending sendwhatsapp...");

        return message;
    }
}