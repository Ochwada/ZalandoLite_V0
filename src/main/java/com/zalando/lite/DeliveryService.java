package com.zalando.lite;

import java.util.*;

/**
 * Handles the assignment of couriers to orders and manages delivery statuses.
 * <p>
 * This service class is responsible for:
 * - Finding available couriers
 * - Creating and returning Delivery objects
 * - Updating the delivery status as the order progresses
 * <p>
 * It simulates a basic logistics workflow and supports logic that could later
 * be extended to support parallel delivery or tracking features.
 * <p>
 * Concepts reinforced:
 * - Search & filtering logic
 * - Business rules
 * - Control flow and state transitions
 */
public class DeliveryService {

    // List of all couriers available to the system
    private List<Courier> couriers;

    public DeliveryService() {
        this.couriers = new ArrayList<>();
    }

    /**
     * Attempts to assign an available courier to the given order.
     * <p>
     * If a courier is found, a Delivery is created and returned.
     * If no couriers are available, returns null or throws an exception (based on design choice).
     *
     * @param order the order that needs to be delivered
     * @return the resulting Delivery object, or null if no couriers available
     */
    public Delivery assignCourier(Order order) {
        for (Courier courier : couriers) {
            if (courier.isAvailable()) { // from Courier
                courier.toggleAvailability(); // mark as busy - from Courier
                order.setCourier(courier); // Assign courier to order - from Order
                return new Delivery(order, courier); // instantiation of new Delivery object with order and courier.
            }
        }
        System.out.println("No couriers available for order: " + order.getOrderId());
        return null;
    }

    /**
     * Updates the status of an existing delivery.
     * <p>
     * Could be "Pending" → "In Transit" → "Delivered"
     *
     * @param delivery  the delivery object to update
     * @param newStatus the new status string
     */
    public void updateDeliveryStatus(Delivery delivery, String newStatus) {
        if (delivery == null || newStatus == null || newStatus.isBlank()) {
            System.err.println(
                    "Invalid input. Delivery or status is missing"
            );
            return;
        }
        String oldStatus = delivery.getStatus();
        delivery.setStatus(newStatus);

        System.out.println("Updated delivery status from \"" + oldStatus + "\" to \"" + newStatus + "\"");
    }

    /**
     * Optional helper method to get all available couriers.
     * <p>
     * Useful for debugging or CLI menus.
     *
     * @return a list of currently available couriers
     */
    public List<Courier> getAvailableCouriers() {
        List<Courier> availableCouriers = new ArrayList<>();

        for (Courier courier : couriers) {
            if (courier.isAvailable()) {
                availableCouriers.add(courier);
            }
        }
        return availableCouriers;
    }
}
