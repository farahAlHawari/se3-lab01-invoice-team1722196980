package s1.lab1;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Invoice 1 ===");

        Invoice first = new Invoice("INV-1", "lina@uni.edu");

        first.addItem("Book", 100.0, 2);

        first.printInvoice();
        first.saveToDatabase("DATABASE");
        first.sendEmail();
        first.sendwhatsapp();

        System.out.println("DATABASE: " + Invoice.DATABASE);
        System.out.println("Total: " + first.calculateTotal());


        System.out.println("\n=== Invoice 2 ===");

        Invoice second = new Invoice("INV-2", "omar@uni.edu");

        second.addItem("Laptop", 1200.0, 1);

        second.printInvoice();
        second.saveToDatabase("FILES");
        second.sendEmail();
        second.sendwhatsapp();

        System.out.println("FILES: " + Invoice.FILES);
        System.out.println("Total: " + second.calculateTotal());


        System.out.println("\n=== Invoice 3 ===");

        Invoice third = new Invoice("INV-3", "lina@uni.edu");

        third.addItem("Item", 500.0, 2);

        third.printInvoice();
        third.saveToDatabase("DATABASE");
        third.sendEmail();
        third.sendwhatsapp();

        System.out.println("DATABASE: " + Invoice.DATABASE);
        System.out.println("Total: " + third.calculateTotal());
    }
}