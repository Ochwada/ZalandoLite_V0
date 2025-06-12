package com.zalando.lite.discountSystem;


import com.zalando.lite.customer.Customer;
import com.zalando.lite.products.Product;

/**
 * *******************************************************
 * Package: com.zalando.lite.discountSystem
 * File: VipDiscout.java
 * Author: Ochwada
 * Date: Friday, 06.Jun.2025, 12:08 PM
 * Description:
 * Objective:
 * *******************************************************
 */


public class VipDiscount extends Discount {

    @Override
    public double calculate(Customer customer, Product product) {
        return customer.isVip() ? 0.10 : 0.0;
    }
}
