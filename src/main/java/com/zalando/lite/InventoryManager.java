package com.zalando.lite;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the store's inventory by storing and modifying products.
 * <p>
 * This class provides functionality to:
 * - Add products to the inventory
 * - Retrieve a product by its ID
 * - List all products
 * - Update product stock when purchases are made
 * <p>
 * It acts as a middle layer between the product data and other services
 * such as order creation or delivery processing.
 * <p>
 * Concepts reinforced:
 * - Collection handling
 * - Looping and search logic
 * - Data mutation (stock updates)
 */
public class InventoryManager {

    // Stores all products currently available in the inventory
    private List<Product> products;

    public InventoryManager() {
        this.products = new ArrayList<>();
    }

    /**
     * Adds a product to the inventory list.
     * <p>
     * This simulates product registration or stock restocking.
     *
     * @param product the product to add to the inventory
     */
    public void addProduct(Product product) {
        
        products.add(product);
    }

    /**
     * Finds a product using its ID.
     * <p>
     * Performs a linear search through the list to locate the product.
     *
     * @param id the ID of the product to find
     * @return the matching product, or null if not found
     */
    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    /**
     * Lists all products in the inventory.
     * <p>
     * Useful for browsing, displaying, or admin use cases.
     *
     * @return list of all products
     */
    public List<Product> listAllProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
        return products;
    }

    /**
     * Reduces the stock of a product after a purchase.
     * <p>
     * Ensures that stock does not fall below zero.
     *
     * @param productId ID of the product to reduce
     * @param quantity  amount to subtract
     * @return true if successful, false if insufficient stock or not found
     */
    public boolean reduceStock(int productId, int quantity) {
        for (Product product : products) {
            if (product.getId() == productId) {
                if (product.getStock() >= quantity) {
                    product.setStock(product.getStock() - quantity);
                    return true;
                } else {
                    System.err.println("Not enough stock for product ID: " + productId);
                    return false;
                }
            }
        }
        System.err.println("Product with ID " + productId + "not found.");
        return false;// product not found
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(this.products); // assuming products is a List<Product>
    }

    /**
     * */


    /**
     * Optional: Check if product exists and is in stock.
     *
     * @param productId the ID to check
     * @return true if product exists and has stock
     */
    public boolean isProductAvailable(int productId) {
        for (Product product : products){
            if (product.getId() == productId ){
                return product.getStock() > 0;
            }
        }
        return false;
    }
}
