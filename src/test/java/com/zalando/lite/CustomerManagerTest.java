package com.zalando.lite;

import com.zalando.lite.customer.Customer;
import com.zalando.lite.managerSystem.CustomerManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CustomerManager} class.
 *
 * These tests verify:
 * - Registering a new customer
 * - Retrieving a customer by ID
 * - Null handling when customer is not found
 *
 * Demonstrates how to use JUnit 5 annotations:
 * - @BeforeAll for one-time setup
 * - @BeforeEach for per-test setup
 * - @AfterEach and @AfterAll for cleanup logic (if needed)
 *
 * Concepts reinforced:
 * - JUnit lifecycle
 * - Assertions for equality and null checks
 * - Testing collections and object references
 */
public class CustomerManagerTest {

    private CustomerManager customerManager;


    @BeforeAll
    static void initAll() {
        // Runs once before all tests
        // Can be used for heavy setup, e.g., static mock data
    }

    @BeforeEach
    void init() {
        // Runs before each test
        // Fresh instance of manager ensures test isolation
        customerManager = new CustomerManager();
    }

    @Test
    void testRegisterAndRetrieveCustomer() {
        // Create a new Customer
        Customer customer = new Customer("Linda", "linda@gmx.de", true);
        // Register it
        customerManager.registerCustomer(customer);
        int customerId = customer.getId();

        // Retrieve it by ID and assert it's the same
        Customer retrieved  = customerManager.getCustomerById(customerId);

        // Assert it's same customer
        assertNotNull(retrieved);
        assertEquals(customer.getId(), retrieved.getId());
        assertEquals(customer.getEmail(), retrieved.getEmail());
        assertEquals(customer.getName(), retrieved.getName());
        assertEquals(customer.isVip(), retrieved.isVip());

    }

    @Test
    void testRetrieveNonExistentCustomerReturnsNull() {
        // Try getting a customer with an unused ID
        Customer results = customerManager.getCustomerById(99);
        // Assert that the result is null
        assertNull(results);
    }

    @AfterEach
    void tearDown() {
        // Runs after each test
        // Could be used to clear static data if shared
    }

    @AfterAll
    static void tearDownAll() {
        // Runs once after all tests
        // Used for global cleanup (e.g., closing DB connections)
    }
}
