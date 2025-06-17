package com.zalando.lite.products;


import java.util.HashMap;
import java.util.Map;

/**
 * *******************************************************
 * Package: com.zalando.lite.products
 * File: FactoryMethods.java
 * Author: Ochwada
 * Date: Friday, 13.Jun.2025, 9:26 AM
 * Description: Factory Methods for clothing products
 * Objective:
 * *******************************************************
 */


public class FactoryMethods {
    public static Product createClothingProduct(
            String name,
            String category,
            double price,
            int stock,
            Map<Product.CLOTH_SIZE, Integer> clothingStock ) {

     Product product = new Product(name, category, price, stock);
     //product.clothingStock() = new HashMap<>(clothingStock);

        return null;

    }
}
