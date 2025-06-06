package com.zalando.lite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The entry point of the ZalandoLite application.
 * <p>
 * This class is where the entire system starts. Eventually, this will become
 * an interactive console app that:
 * <p>
 * - Displays a menu of actions (view products, place orders, etc.)
 * - Reads user input using Scanner
 * - Connects to manager classes like InventoryManager, OrderManager, etc.
 * <p>
 * Right now, it simply prints a welcome message — but it will evolve into
 * your command-line interface (CLI).
 * <p>
 * Concepts reinforced:
 * - Main method structure
 * - Execution flow
 * - Integration of system components
 */
public class Main {

    public static void main(String[] args) {
        // Startup message to indicate the system has launched
        System.out.println("Welcome to ZalandoLite!");

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        // Display menu options and call manager methods

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Create Order");
            System.out.println("4. View Orders");
            System.out.println("5. Export Delivery Report");
            System.out.println("6. View All Customers");
            System.out.println("7. View All Products and Stock");
            System.out.println("8. Undo Last Action");
            System.out.println("9. Simulate Deliveries (Parallel Threads)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();


            switch (choice) {
                case "1" -> {
                    addCustomer();
                    actionManager.recordAction("Customer Added");
                }
                case "2" -> {
                    addProduct();
                    actionManager.recordAction("Product Added");
                }
                case "3" -> {
                    createOrder();
                    actionManager.recordAction("Order Created");
                }
                case "4" -> {
                    viewOrders();
                    actionManager.recordAction("Order Viewed");
                }
                case "5" -> {
                    exportDeliveryReport();
                    actionManager.recordAction("Exported Delivery Report");
                }
                case "6" -> {
                    viewCustomers();
                    actionManager.recordAction("View All Customers");
                }
                case "7" -> {
                    viewProducts();
                    actionManager.recordAction("View All Products");
                }
                case "8" -> {
                    if (actionManager.hasHistory()) {
                        actionManager.undoLastAction();
                    } else {
                        System.out.println("Nothing to undo.");
                    }
                }
                case "9" -> {
                    simulateDeliveries();
                    actionManager.recordAction("Simulated Parallel Deliveries");
                }
                case "0" -> {
                    running = false;
                    System.out.println("👋 Exiting ZalandoLite. Goodbye!");
                }
                default -> System.err.println("❗ Invalid choice. Please select a valid option.\"");
            }
        }


    }


    private static final CustomerManager customerManager = new CustomerManager();
    private static final InventoryManager inventoryManager = new InventoryManager();

    private static final OrderManager orderManager = new OrderManager(inventoryManager);
    private static final ReportManager reportManager = new ReportManager();
    private static final DeliveryService deliveryService = new DeliveryService();
    private static final ActionManager actionManager = new ActionManager();

    // private static final EntityManager<Customer> customerManager = new EntityManager<>();
    // private static final EntityManager<Product> productManager = new EntityManager<>();

    public static Scanner scanner = new Scanner(System.in);

    // ------- Case 9: View Orders -------
    public static void simulateDeliveries() {
        System.out.println("Enter number of deliveries to simulate:");
        int count = Integer.parseInt(scanner.nextLine());

        List<DeliveryThread> threads = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Order dummyOrder = new Order();
            Delivery delivery = deliveryService.assignCourier(dummyOrder);

            if (delivery != null) {
                DeliveryThread thread = new DeliveryThread(delivery);
                threads.add(thread);
                thread.start(); // start delivery thread
            } else {
                System.out.println("No available courier for delivery #" + (i + 1));
            }
        }

        // Wait for all threads to complete
        for (DeliveryThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted.");
            }
        }

        System.out.println("All deliveries completed (if couriers were available).");
    }

    // ------- Case 7: View Orders -------
    public static void viewProducts() {
        List<Product> products = inventoryManager.getAllProducts(); // uses shared inventoryManager

        if (products == null || products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("--- Product Inventory ---");
        for (Product product : products) {
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Category: " + product.getCategory());
            System.out.printf("Price: $%.2f\n", product.getPrice());
            System.out.println("Stock: " + product.getStock());
            System.out.println("-----");
        }
    }

    // ------- Case 6 : View Orders-------
    public static void viewCustomers() {

        Map<Integer, Customer> customers = customerManager.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("--- Registered Customers ---");
            for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
                Customer customer = entry.getValue();

                System.out.println("ID: " + entry.getKey());
                System.out.println("Name: " + customer.getName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("VIP: " + (customer.isVip() ? "Yes" : "No"));
                System.out.println("-----");
            }
        }
    }

    // ------- Case 5 : View Orders -------
    public static void exportDeliveryReport() {

        String reportPath = reportManager.getDefaultReportPath();

        List<Courier> couriers = deliveryService.getAvailableCouriers();
        List<Delivery> allDeliveries = new ArrayList<>();

        for (Courier courier : couriers) {
            // Create a dummy order (or get actual order if available)
            Order order = new Order(); // You must have a constructor or a mock Order object
            Delivery delivery = new Delivery(order, courier);
            delivery.setStatus("Pending"); // Set default status
            allDeliveries.add(delivery);
        }
        reportManager.exportDeliveryReport(allDeliveries, reportPath);

    }

    //-------  Case 4 : View Orders -------
    public static void viewOrders() {

        System.out.println("Enter customer ID to view order: ");
        int viewId = Integer.parseInt(scanner.nextLine());

        List<Order> customerOrders = orderManager.getOrdersForCustomer(viewId);

        if (customerOrders == null || customerOrders.isEmpty()) {
            System.out.println("No orders found for customer " + viewId);
        } else {
            for (Order o : customerOrders) {
                System.out.println(o);
            }
        }
        // Prints out order summary statistics
        double revenue = orderManager.getTotalRevenue();
        System.out.printf("Total Revenue: €%.2f%n", revenue);

        double avg = orderManager.getMeanOrderValue();
        System.out.printf("Average Order Value: €%.2f%n", avg);

        Order topOrder = orderManager.getHighestValueOrder();
        System.out.printf("Top Order Total: €%.2f%n", (topOrder != null ? topOrder.calculateTotal() : 0.0));
    }

    // -------  Case 3 : CreateOrder -------
    public static void createOrder() {

        System.out.println("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());

        Customer orderCustomer = customerManager.getCustomerById(customerId);

        if (orderCustomer == null) {
            System.err.println("Customer not found. Exiting...");
            return;// Stop processing
        }

        List<OrderItem> orderItems = new ArrayList<>();

        while (true) {
            System.out.println("Enter product ID (or 0 to finish): ");
            int productId = Integer.parseInt(scanner.nextLine());

            if (productId == 0) break;

            Product product = inventoryManager.findProductById(productId);

            if (product == null) {
                System.err.println("Product not found. Please try again");
                continue; // skip to next iteration
            }
            //assert product != null: "Product must not be null";

            System.out.println("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (quantity > product.getStock()) {
                System.err.println("Not enough stock. Available: " + product.getStock());
                continue; // Allow retry
            }

            // Reduce stock
            //product.setStock(product.getStock() - quantity);

            orderItems.add(new OrderItem(product, quantity));
            System.out.println("Added to order: " + product.getName() + " x " + quantity);
        }

        if (orderItems.isEmpty()) {
            System.err.println("No items selected. Order not created.");
            return;
        }

        Order newOrder = orderManager.createOrder(orderCustomer, orderItems);

        if (newOrder != null) {
            Delivery delivery = deliveryService.assignCourier(newOrder);
            System.out.println("✅ Order created with ID: " + newOrder.getOrderId());

            if (delivery != null) {
                System.out.println("Courier " + delivery.getCourier().getName() + " assigned.");
            } else {
                System.out.println("No available couriers. Delivery will be delayed.");
            }
        } else {
            System.out.println("Order creation failed.");
        }
    }

    // ------- Case 2 : Add Product -------
    public static void addProduct() {
        System.out.println("Enter Product name: ");
        String pname = scanner.nextLine();

        System.out.println("Category: ");
        String category = scanner.nextLine();

        System.out.println("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        Product product = new Product(pname, category, price, stock);
        inventoryManager.addProduct(product);
        System.out.println("Product added.");
    }

    // ------- Case 1 : Add Customer -------
    public static void addCustomer() {
        boolean isVip = false;

        System.out.println("Enter Customer name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Email: ");
        String email = scanner.nextLine();

        System.out.println("Is VIP? (yes/no) ");
        String vipInput = scanner.nextLine().trim().toLowerCase();

        if (vipInput.equals("yes") || vipInput.equals("y")) {
            isVip = true;
        } else if (vipInput.equals("no") || vipInput.equals("n")) {
            isVip = false;
        } else {
            System.out.println("Please enter 'yes' or 'no'.");
        }

        Customer customer = new Customer(name, email, isVip);
        customerManager.registerCustomer(customer);
        System.out.println("Customer added.");
    }
}



