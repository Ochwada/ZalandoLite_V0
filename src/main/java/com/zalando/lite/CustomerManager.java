package com.zalando.lite;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the registration and lookup of customers in the ZalandoLite system.
 * <p>
 * This class acts as a "service layer" that stores customer data, retrieves it efficiently,
 * and ensures that the application can quickly access user information by ID.
 * <p>
 * Internally uses a Map for fast lookup and future extensibility (like updates or filtering).
 * <p>
 * Concepts reinforced:
 * - Maps (for ID → object relationships)
 * - Null safety
 * - Separation of concerns (manager vs model)
 */
public class CustomerManager {

    // Stores customers using their ID as the key for quick access
    private Map<Integer, Customer> customers;

    public CustomerManager(Map<Integer, Customer> customers) {
        this.customers = new HashMap<>();
    }

    public CustomerManager() {
        this.customers = new HashMap<>(); // initialize the map
    }

    /**
     * Registers a new customer in the system.
     * If the ID already exists, the existing customer is overwritten.
     *
     * @param customer the customer to add
     */
    public void registerCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param id the customer’s ID
     * @return the matching Customer, or null if not found
     */
    public Customer getCustomerById(int id) {
        if (customers.containsKey(id)) {
            return customers.get(id);
        } else {
            System.err.println("No Customer by that ID (" + id + ")");
            return null;
        }
    }

    /**
     * Optional: Returns all registered customers.
     * Can be used for listing or reporting.
     *
     * @return map of all customers
     */
    public Map<Integer, Customer> getAllCustomers() {
        return new HashMap<>(customers);
    }
}
