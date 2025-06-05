package com.zalando.lite;

import java.util.*;


/**
 * Handles the creation, validation, and storage of orders in the ZalandoLite system.
 * <p>
 * This manager:
 * - Accepts a list of items and a customer to create a new {@link Order}
 * - Checks inventory levels before processing the order
 * - Updates stock accordingly
 * - Stores and retrieves orders per customer
 * <p>
 * Serves as the glue between the inventory and customer layers.
 * <p>
 * Concepts reinforced:
 * - Aggregation and system coordination
 * - Control flow with validation
 * - Data structure usage (Map for customer orders)
 */
public class OrderManager {

    // Stores orders for each customer (keyed by customer ID)
    private Map<Integer, List<Order>> customerOrders;

    // Used to update inventory after order placement
    private InventoryManager inventoryManager;

    public OrderManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        this.customerOrders = new HashMap<>();
    }

    /**
     * Creates a new order for the given customer and list of order items.
     * <p>
     * Validates product availability before allowing the order to proceed.
     *
     * @param customer the customer placing the order
     * @param items    a list of OrderItem entries to be purchased
     * @return the created Order, or null if validation fails
     */
    public Order createOrder(Customer customer, List<OrderItem> items) {
        // Check for null or empty input
        if (customer == null || items == null || items.isEmpty()) {
            System.err.println("Invalid Order Input");
            return null;
        }

        // Validate availability for each item
        if (!validateStock(items)) {
            return null; // validation failed
        }

        // If the validation passes, reduce stock
        updateInventory(items);

        // Create and save Order
        Order order = new Order(customer, items);

        List<Order> orders = customerOrders.get(customer.getId());
        if (orders == null) {
            orders = new ArrayList<>();
            customerOrders.put(customer.getId(), orders);
        }
        orders.add(order);

        System.out.println("Order created for " + customer.getName());
        return order;

    }

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list of their orders, or an empty list if none exist
     */
    public List<Order> getOrdersForCustomer(int customerId) {
        return customerOrders.getOrDefault(
                customerId, new ArrayList<>()
        );
    }

    /**
     * Optional helper: Validates item quantities before processing.
     *
     * @param items the list of items to validate
     * @return true if all items are in stock
     */
    private boolean validateStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            int orderedQty = item.getQuantity();

            if (product.getStock() < orderedQty) {
                System.err.println("Not enough " + product.getName() + " in stock ");
                return false;
            }
        }
        return true;
    }

    /**
     * Optional helper: Updates inventory after successful order.
     *
     * @param items the list of items whose stock should be reduced
     */
    private void updateInventory(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            int orderedQty = item.getQuantity();

            this.inventoryManager.reduceStock(product.getId(), orderedQty);
        }
    }
}
