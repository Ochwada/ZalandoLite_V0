package com.zalando.lite;

import com.zalando.lite.managerSystem.*;


import java.util.*;

import static com.zalando.lite.HelperMain.*;

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

    private static final ActionManager actionManager = new ActionManager();

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


}



