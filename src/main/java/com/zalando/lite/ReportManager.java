package com.zalando.lite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Handles writing delivery reports to a text file.
 *
 * This class is responsible for exporting summaries of deliveries,
 * which may include courier, order, customer, and status information.
 *
 * It demonstrates:
 * - Java File I/O
 * - Try-with-resources pattern
 * - Handling checked exceptions (IOException)
 *
 * Concepts reinforced:
 * - File writing
 * - Resource management
 * - Exception handling
 */
public class ReportManager {

    /**
     * Writes a list of deliveries to a text file.
     *
     * Each delivery is written on a new line using its toString() method.
     *
     * @param deliveries list of completed or active deliveries
     * @param filePath path to the file where the report should be saved
     */
    public void exportDeliveryReport(List<Delivery> deliveries, String filePath) {
        // Use FileWriter wrapped in try-with-resources
        // Loop over deliveries and write each one to a new line
        // Catch and handle IOException with a user-friendly message

        try(FileWriter writer = new FileWriter(filePath)){
            for (Delivery delivery: deliveries){
                writer.write(delivery.toString());
                writer.write(System.lineSeparator()); // newline
            }
            System.out.println("Delivery report exported to: " + filePath);
        }catch (IOException e){
            System.out.println(" Failed to write delivery report " + e.getMessage());
        }
    }

    /**
     * Optional: Helper to generate a default file path based on timestamp.
     *
     * @return a recommended file path for report export
     */
    public String getDefaultReportPath() {
        // Return a file name like "delivery-report-2025-05-30.txt"
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = now.format(formatter);

        return "delivery-report-" + timestamp + ".txt";
    }
}
