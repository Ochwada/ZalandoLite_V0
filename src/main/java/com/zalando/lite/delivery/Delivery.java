package com.zalando.lite.delivery;

import com.zalando.lite.courier.Courier;
import com.zalando.lite.order.Order;

import java.time.LocalDateTime;

/**
 * Represents a delivery that connects an {@link Order} with a {@link Courier}.
 * <p>
 * This class is used to simulate real-world delivery scenarios in the ZalandoLite system.
 * Each delivery has a timestamp and a mutable status string to indicate progress.
 * <p>
 * Concepts reinforced:
 * - Object composition (Order and Courier together)
 * - Timestamping with LocalDateTime
 * - State modeling
 */
public class Delivery {

    // The order that is being delivered
    private Order order;

    // The courier assigned to deliver the order
    private Courier courier;

    // Delivery status (e.g., "Pending", "In Transit", "Delivered")
    private String status;

    // The date and time the delivery was created
    private LocalDateTime timestamp;

    /**
     * Constructs a Delivery with an associated order and courier.
     * Automatically sets the timestamp and default status ("Pending").
     */
    public Delivery(Courier courier, String status, LocalDateTime timestamp, Order order) {
        this.courier = courier;
        this.status = "Pending";
        this.timestamp = LocalDateTime.now();
        this.order = order;
    }

    public Delivery(Order order, Courier courier) {
        this.order = order;
        this.courier = courier;
        this.status = "Pending";
    }

    // Returns the order associated with the delivery
    public Order getOrder() {
        return order;
    }

    // Sets the order associated with the delivery
    public void setOrder(Order order) {
        this.order = order;
    }

    // Returns the courier assigned to this delivery
    public Courier getCourier() {
        return courier;
    }

    // Sets the courier assigned to this delivery
    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    // Returns the current status of the delivery
    public String getStatus() {
        return status;
    }

    // Updates the status (should reflect progress or failure)
    public void setStatus(String status) {
        this.status = status;
    }

    // Returns the timestamp when the delivery was created
    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }

    /**
     * Provides a simple string representation of the delivery details.
     * Useful for debugging and logging to file.
     */
    @Override
    public String toString() {
        return "Delivery{" +
                "order=" + order +
                ", courier=" + courier +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
