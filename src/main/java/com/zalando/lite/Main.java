package com.zalando.lite;

import java.util.ArrayList;
import java.util.List;
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
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();


            switch (choice) {
                case "1" -> addCustomer();
                case "2" -> addProduct();
                case "3" -> createOrder();
                case "4" -> viewOrders();
                case "5" -> exportDeliveryReport();
                case "0" -> {
                    running = false;
                    System.out.println("👋 Exiting ZalandoLite. Goodbye!");
                }
                default -> System.err.println("❗ Invalid choice. Please select a valid option.\"");
            }
        }


    }

    // Case 5 : View Orders
    public static void exportDeliveryReport() {
        ReportManager reportManager = new ReportManager();
        DeliveryService deliveryService = new DeliveryService();

        String reportPath = reportManager.getDefaultReportPath();

        List<Courier> couriers = deliveryService.getAvailableCouriers();
        List<Delivery> allDeliveries = new ArrayList<>();

        for (Courier courier : couriers){
            // Create a dummy order (or get actual order if available)
            Order order = new Order(); // You must have a constructor or a mock Order object
            Delivery delivery = new Delivery(order, courier);
            delivery.setStatus("Pending"); // Set default status
            allDeliveries.add(delivery);
        }
        reportManager.exportDeliveryReport(allDeliveries, reportPath);

    }

    // Case 4 : View Orders
    public static void viewOrders() {
        Scanner scanner = new Scanner(System.in);

        InventoryManager inventoryManager = new InventoryManager();
        OrderManager orderManager = new OrderManager(inventoryManager);


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
    }

    // Case 3 : CreateOrder
    public static void createOrder() {
        Scanner scanner = new Scanner(System.in);

        CustomerManager customerManager = new CustomerManager();
        InventoryManager inventoryManager = new InventoryManager();
        OrderManager orderManager = new OrderManager(inventoryManager);
        DeliveryService deliveryService = new DeliveryService();


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

            OrderItem item = new OrderItem(product, quantity);
            orderItems.add(item);
        }

        if (orderItems.isEmpty()) {
            System.err.println("No items selected");
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

    // Case 2 : Add Product
    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        InventoryManager inventoryManager = new InventoryManager();

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

    // Case 1 : Add Customer
    public static void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        boolean isVip = false;
        CustomerManager customerManager = new CustomerManager();

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



