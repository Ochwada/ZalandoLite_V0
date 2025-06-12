package com.zalando.lite.discountSystem;


import com.zalando.lite.customer.Customer;
import com.zalando.lite.products.Product;

/**
 * *******************************************************
 * Package: com.zalando.lite.discountSystem
 * File: Discount.java
 * Author: Ochwada
 * Date: Friday, 06.Jun.2025, 12:08 PM
 * Description:
 * Objective:
 * *******************************************************
 */


public abstract class Discount {

    public abstract double calculate(Customer customer, Product product);
}
