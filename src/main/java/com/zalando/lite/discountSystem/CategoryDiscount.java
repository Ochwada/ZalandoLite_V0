package com.zalando.lite.discountSystem;


import com.zalando.lite.customer.Customer;
import com.zalando.lite.products.Product;

import java.util.*;

/**
 * *******************************************************
 * Package: com.zalando.lite.discountSystem
 * File: CategoryDiscount.java
 * Author: Ochwada
 * Date: Friday, 06.Jun.2025, 12:08 PM
 * Description:
 * Objective:
 * *******************************************************
 */


public class CategoryDiscount extends Discount {

    private static final Map<String, Double> categoryDiscounted = Map.of(
            "electronics", 0.5,
            "shirt", 0.10,
            "jackets", 0.45,
            "shoes", 0.20
    );

    @Override
    public double calculate(Customer customer, Product product) {
        return categoryDiscounted.getOrDefault(product.getCategory().toLowerCase(), 0.0);
    }

    // ---------- Moved from Discount Manager -----------

    /**
     * Checks if the product qualifies for a category-based discount.
     *
     * @param product the product to check
     * @return true if the category matches discount rules
     */
    public static boolean isCategoryDiscounted(Product product) {
        String category = product.getCategory().toLowerCase();

        return categoryDiscounted.containsKey(category);
    }

}
