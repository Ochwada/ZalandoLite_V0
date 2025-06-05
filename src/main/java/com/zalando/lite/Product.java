package com.zalando.lite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zalando.lite.annotations.Featured;

/**
 * Represents a product in the ZalandoLite fashion store.
 * <p>
 * Each product has:
 * - A unique ID
 * - A name and category
 * - A price
 * - Current stock level
 * - A list of available sizes (e.g., S, M, L)
 * <p>
 * Some products may also be marked with the {@link Featured} annotation
 * to indicate they are promoted or highlighted in the catalog.
 * <p>
 * Concepts reinforced:
 * - POJO modeling
 * - Encapsulation
 * - Lists
 * - Annotation usage
 */
public class Product {

    // Unique identifier for the product
    private int id;

    // Product name (e.g., "Leather Jacket")
    private String name;

    // Category for grouping (e.g., "Shoes", "Accessories", "Jackets")
    private String category;

    // Price of the product
    private double price;

    // Quantity in stock (used by InventoryManager and OrderManager)
    private int stock;

    // List of size options (e.g., "S", "M", "L")
    private List<SIZE> availableSizes;

    public enum SIZE {
        XS, S, M, L, XL, XXL
    }

    /**
     * Constructor to initialize the product fields.
     * Used during product setup or inventory population.
     */

    public Product(int id, String name, String category, double price, int stock, List<SIZE> availableSizes) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.availableSizes = new ArrayList<>(); // initialize the array list of SIZE.
    }

    // Empty Constructor
    public Product() {
        // no-arg constructor
    }
    /**
     * Getters and Setters of */

    // Getter and setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and setter for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter and setter for stock quantity
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter and setter for size options
    public List<SIZE> getAvailableSizes() {
        return Collections.unmodifiableList(availableSizes);
    }

    public void setAvailableSizes(List<SIZE> sizes) {
        this.availableSizes = new ArrayList<>(sizes);
    }

    /**
     * Optional method to check if the product is out of stock.
     *
     * @return true if stock is zero or less
     */
    public boolean isOutOfStock() {
        return getStock() <= 0;
    }

    /**
     * Returns a string summary of the product’s details.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", availableSizes=" + availableSizes +
                '}';
    }
}
