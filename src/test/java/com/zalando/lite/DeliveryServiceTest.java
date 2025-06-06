package com.zalando.lite;

import org.junit.jupiter.api.*;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link DeliveryService} class.
 * <p>
 * These tests verify:
 * - Courier assignment works when couriers are available
 * - No courier is assigned if all are busy
 * - Delivery status can be updated correctly
 * <p>
 * Also demonstrates the use of JUnit 5 annotations:
 * - @BeforeEach: setup before every test
 * - @AfterEach: clean up after each test
 * <p>
 * Concepts reinforced:
 * - Testing with mock data
 * - Assertions on object behavior
 * - Booleans and state change testing
 */
public class DeliveryServiceTest {

    private DeliveryService deliveryService;
    private Courier availableCourier;
    private Courier busyCourier;
    private Order dummyOrder;

    @BeforeEach
    void setUp() {
        //  Instantiate DeliveryService
        deliveryService = new DeliveryService();

        // Create mock couriers (1 available, 1 unavailable)
        availableCourier = new Courier("Alex", "Volvo", true);   // true = available
        busyCourier = new Courier("Jamie", "BMW", false);      // false = unavailable

        deliveryService.addCourier(availableCourier);
        deliveryService.addCourier(busyCourier);

        //------ Create a mock Order object --------
        // Create mock customer and product
        Customer customer = new Customer("Linda", "linda@example.com", true);
        Product product = new Product("Shirt", "clothes", 29.99, 10);
        OrderItem orderItem = new OrderItem(product, 2);

        // Create a mock Order with one item
        dummyOrder = new Order(customer, List.of(orderItem));

    }

    @Test
    void testAssignCourierWhenAvailable() {
        //  Assign courier using deliveryService
        Delivery delivery = deliveryService.assignCourier(dummyOrder);
        // Assert that a delivery object is returned
        assertNotNull(delivery, "Delivery should not be null when an available courier exists.");

        // Extract assigned courier
        Courier assignedCourier = delivery.getCourier();

        // Assert that a courier was actually assigned
        assertNotNull(assignedCourier, "Courier should be assigned to the delivery.");

        // Assert that the courier is the expected one (the available one)
        assertEquals(availableCourier.getName(), assignedCourier.getName(), "The available courier should be assigned.");

        // Assert that the courier is now marked as unavailable
        assertFalse(assignedCourier.isAvailable(), "Assigned courier should now be unavailable.");
    }

    @Test
    void testAssignCourierWhenNoneAvailable() {
        // Make all couriers unavailable
        availableCourier.setAvailable(false);
        busyCourier.setAvailable(false); // already false, but just to be sure

        // Try assigning a courier to the dummy order
        Delivery result = deliveryService.assignCourier(dummyOrder);

        // Assert that no delivery was made
        assertNull(result, "Delivery should be null when no couriers are available.");
    }

    @Test
    void testUpdateDeliveryStatus() {
        Delivery delivery = deliveryService.assignCourier(dummyOrder);
        assertNotNull(delivery, "Delivery should not be null when a courier is assigned.");

        deliveryService.updateDeliveryStatus(delivery, "Delivered");

        assertEquals("Delivered", delivery.getStatus(), "Delivery status should be updated to 'Delivered'.");}

    @AfterEach
    void tearDown() {
        // Re-initialize or clear couriers list inside the deliveryService, if needed
        deliveryService = null;
        availableCourier = null;
        busyCourier = null;
        dummyOrder = null;
    }
}
