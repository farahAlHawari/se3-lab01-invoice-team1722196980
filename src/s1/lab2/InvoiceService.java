package s1.lab2;

public class InvoiceService {

    private final InvoiceCalculator calculator;
    private final InvoiceFormatter formatter;
    private final InvoiceRepository repository;

    private final InvoiceNotifier emailNotifier;
    private final InvoiceNotifier whatsappNotifier;

    public InvoiceService(
            InvoiceCalculator calculator,
            InvoiceFormatter formatter,
            InvoiceRepository repository,
            InvoiceNotifier emailNotifier,
            InvoiceNotifier whatsappNotifier) {

        this.calculator = calculator;
        this.formatter = formatter;
        this.repository = repository;
        this.emailNotifier = emailNotifier;
        this.whatsappNotifier = whatsappNotifier;
    }

    public void issue(Invoice invoice) {

        double total = calculator.total(invoice);

        String text = formatter.format(invoice, total);

        System.out.println(text);

        repository.save(invoice, total);

        emailNotifier.send(
                invoice.getCustomerEmail(),
                "Invoice " + invoice.getNumber(),
                text
        );

        whatsappNotifier.send(
                "sendwhatsapp",
                "Invoice " + invoice.getNumber(),
                text
        );
    }
}