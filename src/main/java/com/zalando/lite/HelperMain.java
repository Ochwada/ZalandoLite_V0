package com.zalando.lite;

import com.zalando.lite.courier.Courier;

import com.zalando.lite.customer.Customer;

import com.zalando.lite.delivery.DeliveryService;
import com.zalando.lite.delivery.DeliveryThread;
import com.zalando.lite.delivery.Delivery;

import com.zalando.lite.managerSystem.*;

import com.zalando.lite.order.Order;
import com.zalando.lite.order.OrderItem;

import com.zalando.lite.products.Product;

import java.util.*;

/**
 * *******************************************************
 * Package: PACKAGE_NAME
 * File: com.zalando.lite.HelperMain.java
 * Author: Ochwada
 * Date: Thursday, 19.Jun.2025, 9:05 AM
 * Description: Helper functions to be used in Demo class
 * Objective:
 * *******************************************************
 */


public class HelperMain {
    private static final CustomerManager customerManager = new CustomerManager();
    private static final InventoryManager inventoryManager = new InventoryManager();

    private static final OrderManager orderManager = new OrderManager(inventoryManager);
    private static final ReportManager reportManager = new ReportManager();
    private static final DeliveryService deliveryService = new DeliveryService();

    // private static final EntityManager<Customer> customerManager = new EntityManager<>();
    // private static final EntityManager<Product> productManager = new EntityManager<>();

    public static Scanner scanner = new Scanner(System.in);




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

    // ------- Case 2 : Add Product -------
    public static void addProduct() {
        System.out.println("Enter Product name: ");
        String pname = scanner.nextLine();

        System.out.println("Category: ");
        String category = scanner.nextLine().toLowerCase();

        System.out.println("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Stock: ");
        //int stock = Integer.parseInt(scanner.nextLine());
        int stock = scanner.nextInt();
        scanner.nextLine();

//        Product product;
//
//        if (category.equals("clothing")){
//            Map<Product.CLOTH_SIZE, Integer> clothingStock = new HashMap<>();
//
//            for (Product.CLOTH_SIZE size : Product.CLOTH_SIZE.values()){
//                System.out.println("Enter stock for size " + size + ": ");
//
//                int sizeStock = scanner.nextInt();
//                clothingStock.put(size, sizeStock);
//            }
//            scanner.nextLine();
//            product = Product.
//        }


        Product product = new Product(pname, category, price, stock);


        inventoryManager.addProduct(product);
        System.out.println("Product added.");
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
}
