package com.zalando.lite.delivery;


/**
 * *******************************************************
 * Package: com.zalando.lite
 * File: DeliveryThread.java
 * Author: Ochwada
 * Date: Friday, 06.Jun.2025, 12:38 PM
 * Description:
 * Objective:
 * *******************************************************
 */


public class DeliveryThread extends Thread {

    private final Delivery delivery;

    /**
     * Constructs a delivery thread with a specific delivery.
     *
     * @param delivery the delivery to simulate
     */
    public DeliveryThread(Delivery delivery) {
        this.delivery = delivery;
    }



    /**
     * Runs the delivery simulation.
     * <p>
     * This method:
     * - Prints a start message
     * - Waits for a fixed time (5 seconds) to simulate delivery
     * - Updates delivery status
     * - Marks courier as available again
     * </p>
     */

    @Override
    public void run() {
        System.out.println("Starting delivery for Order #" +
                delivery.getOrder().getOrderId() +
                " by Courier " +
                delivery.getCourier().getName());

        try {
            Thread.sleep(5000); // Simulate delivery time

        } catch (InterruptedException e) {

            System.err.println("Delivery interrupted for Order #" + delivery.getOrder().getOrderId());
        }

        delivery.setStatus("Delivered");

        delivery.getCourier().toggleAvailability(); // Mark courier as available again

        System.out.println(" Order #" + delivery.getOrder().getOrderId() + " delivered successfully!");
    }
}
