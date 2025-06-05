package com.zalando.lite;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a customer's order in the ZalandoLite system.
 * <p>
 * Each order includes:
 * - A unique order ID
 * - A reference to the customer who placed the order
 * - A list of line items (products and their quantities)
 * - A timestamp of when the order was placed
 * <p>
 * This class is central to the business logic and ties together customers,
 * products, and delivery.
 * <p>
 * Concepts reinforced:
 * - Object composition
 * - Use of lists to store related data
 * - Timestamps using LocalDateTime
 */
public class Order {

    // Unique identifier for the order
    private int orderId;

    // The customer who placed the order (reference, not a copy)
    private Customer customer;

    // A list of items included in the order
    private List<OrderItem> items;

    // The date and time the order was created
    private LocalDateTime orderDate;

    // ADDING COURIER
    private Courier courier;
    /**
     * Constructor to initialize an order with a customer and list of items.
     * <p>
     * Automatically sets the order date to the current timestamp.
     */


    public Order(Customer customer, List<OrderItem> items, LocalDateTime orderDate, Courier courier, int orderId) {
        this.customer = customer;
        this.items = items;
        this.orderDate = LocalDateTime.now();
        this.courier = courier;
        this.orderId = orderId;
    }


    public Order() {
    }

    public Order(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
    }


    // Returns the order ID
    public int getOrderId() {
        return orderId;
    }

    // Sets the order ID (may be used when generating orders manually)
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Returns the customer who placed the order
    public Customer getCustomer() {
        return customer;
    }

    // Sets the customer for the order
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Returns the list of order items
    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // Sets the list of order items
    public void setItems(List<OrderItem> items) {
        this.items = new ArrayList<>(items);
    }

    // Returns the timestamp of when the order was placed
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    // Sets the order timestamp (usually auto-generated)
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = LocalDateTime.now();
    }

    // ADDING COURIER -setter and Getter (for assigning courier to order)
    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    /**
     * Calculates the total cost of the order by summing
     * (product price × quantity) for each line item.
     *
     * @return the total order cost
     */
    public double calculateTotal() {
        double total = 0.00;
        for (OrderItem item : items){
            total = item.getSubtotal();
        }
        return total;
    }

    /**
     * Returns a string summary of the order.
     * Useful for displaying in CLI or logs.
     */
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", items=" + items +
                ", orderDate=" + orderDate +
                '}';
    }
}
