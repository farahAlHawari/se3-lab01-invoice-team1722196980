package s1.lab2;

public class Main {

    public static void main(String[] args) {

        InvoiceCalculator calculator = new InvoiceCalculator();
        InvoiceFormatter formatter = new InvoiceFormatter();

        DatabaseInvoiceRepository database =
                new DatabaseInvoiceRepository();

        FileInvoiceRepository files =
                new FileInvoiceRepository();

        InvoiceNotifier email = new EmailInvoiceNotifier();
        InvoiceNotifier whatsapp = new WhatsappInvoiceNotifier();

        InvoiceService dbService = new InvoiceService(
                calculator,
                formatter,
                database,
                email,
                whatsapp
        );

        InvoiceService fileService = new InvoiceService(
                calculator,
                formatter,
                files,
                email,
                whatsapp
        );

        System.out.println("=== Invoice 1 ===");

        Invoice first = new Invoice("INV-1", "lina@uni.edu");
        first.addItem("Book", 100.0, 2);

        dbService.issue(first);

        System.out.println("DATABASE: " + database.rows());
        System.out.println("Total: " + calculator.total(first));


        System.out.println("\n=== Invoice 2 ===");

        Invoice second = new Invoice("INV-2", "omar@uni.edu");
        second.addItem("Laptop", 1200.0, 1);

        fileService.issue(second);

        System.out.println("FILES: " + files.rows());
        System.out.println("Total: " + calculator.total(second));


        System.out.println("\n=== Invoice 3 ===");

        Invoice third = new Invoice("INV-3", "lina@uni.edu");
        third.addItem("Item", 500.0, 2);

        dbService.issue(third);

        System.out.println("DATABASE: " + database.rows());
        System.out.println("Total: " + calculator.total(third));
    }
}