package com.zalando.lite;

import com.zalando.lite.annotations.VIP;
import com.zalando.lite.discountSystem.CategoryDiscount;
import com.zalando.lite.discountSystem.Discount;
import com.zalando.lite.discountSystem.VipDiscount;

import java.lang.reflect.Field;
import java.util.*;

import static com.zalando.lite.discountSystem.CategoryDiscount.*;

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

    public double applyDiscount(Customer customer, Product product) {
        double basePrice = product.getPrice();

        List<Discount> discounts = List.of(new VipDiscount(), new CategoryDiscount());
        double totalDiscount = 0.00;

        // check if a category discount is applicable
        if (CategoryDiscount.isCategoryDiscounted(product)) {
            System.out.println("Category discount applicable for: " + product.getCategory());
        }

        for (Discount discount : discounts){
            totalDiscount += discount.calculate(customer, product);
        }

        double finalPrice = basePrice * (1 - totalDiscount);

        System.out.printf("Base price: %2f, Discount: %.0f%%, Final price: %.2f%n",
                basePrice, (totalDiscount * 100), finalPrice);

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


}
