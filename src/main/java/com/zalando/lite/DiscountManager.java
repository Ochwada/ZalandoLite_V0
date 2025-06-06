package com.zalando.lite;

import com.zalando.lite.annotations.VIP;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Applies discount logic for customers and products in the ZalandoLite system.
 * <p>
 * This class uses Java Reflection to check if a customer qualifies for a discount.
 * It also supports category-specific discounts (like for "Shoes").
 * <p>
 * Discounts:
 * - VIP customers: 10% off
 * - Products in "Shoes" category: 20% off
 * <p>
 * Concepts reinforced:
 * - Reflection
 * - Conditional logic
 * - Method extraction & code reuse
 */
public class DiscountManager {

    /**
     * Applies applicable discounts based on customer VIP status and product category.
     * <p>
     * Reflection is used to detect the @VIP annotation on the Customer object.
     *
     * @param customer the customer making the purchase
     * @param product the product being purchased
     * @return the final price after discount
     */

    // Additional discount off a specific product
    Map<String, Double> categoryDiscounted = Map.of(
            "electronics", 0.5,
            "shirt", 0.10,
            "Jackets", 0.45,
            "shoes", 0.20
    );

    public double applyDiscount(Customer customer, Product product) {
        double basePrice = product.getPrice();
        double discount = 0.00;

        // 10% discount on vip
        if (customer.isVip()) {
            discount += 0.10;
        }

        // Category-based discount
        String category = product.getCategory().toLowerCase();
        /*if (categoryDiscounted.containsKey(category)){
            discount += categoryDiscounted.get(category);
        }*/

        discount += categoryDiscounted.getOrDefault(category, 0.0);

        double finalPrice = basePrice * (1 - discount);
        System.out.printf("Base price: %2f, Discount: %.0f%%, Final price: %.2f%n",
                basePrice, (discount * 100), finalPrice);

        return finalPrice;
    }

    /**
     * Checks if a customer has a @VIP annotation.
     * <p>
     * This method uses reflection to access fields marked as VIP and check their values.
     *
     * @param customer the customer to inspect
     * @return true if VIP, false otherwise
     */
    private boolean isVipUsingReflection(Customer customer) {
        try {
            // looping through the fields:
            for (Field field : customer.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(VIP.class)) {
                    field.setAccessible(true); // access to private field values
                    return field.getBoolean(customer); // return the value of the field
                }
            }
        } catch (IllegalAccessException e) {
            System.err.println("Error: " + e.getMessage());

        }
        return false; // if no VIP field or error occurs
    }

    /**
     * Checks if the product qualifies for a category-based discount.
     *
     * @param product the product to check
     * @return true if the category matches discount rules
     */
    private boolean isCategoryDiscounted(Product product) {
        String category = product.getCategory().toLowerCase();
        return categoryDiscounted.containsKey(category);
    }
}
