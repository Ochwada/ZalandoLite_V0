package com.zalando.lite;

import com.zalando.lite.customer.Customer;
import com.zalando.lite.managerSystem.DiscountManager;
import com.zalando.lite.products.Product;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link DiscountManager}.
 *
 * These tests verify:
 * - VIP customers receive the correct discount (10%)
 * - Products in the "Shoes" category receive a 20% discount
 * - Discounts do not stack incorrectly
 * - Non-VIP customers with non-discount categories pay full price
 *
 * Concepts reinforced:
 * - Reflection testing with @VIP annotation
 * - Precision checking with floating point assertions
 * - Conditional logic paths
 */
public class DiscountManagerTest {

    private DiscountManager discountManager;

    @BeforeAll // JUnit runs this once before any test method in this class.
    static void beforeAllTests() {
        System.out.println("\uD83E\uDDFF Stating DiscountManager Tests...  ");
    }

    @BeforeEach
    void setUp() {
        System.out.println("\uD83D\uDD38 New Setup: Create a new object.");
        // Prepare a fresh instance before each test
        discountManager = new DiscountManager();
    }

    @Test
    @DisplayName("testVipDiscountApplied")
    void testVipDiscountApplied() {
        // Create a customer with @VIP annotation
        Customer vipCustomer = new Customer("Linda", "linda@example.com", true);

        // Create a product in a non-discounted category
        Product product = new Product("Java Book", "books", 100.0, 10);
        // Apply discount and assert it's 10% off
        double discountedPrice = discountManager.applyDiscount(vipCustomer, product);
        // 10% of 100 = 90.0
        assertEquals(90.0, discountedPrice, 0.01, "VIP customer should receive a 10% discount.");
    }

    @Test
    @DisplayName("testCategoryDiscountApplied")
    void testCategoryDiscountApplied() {
        // Create a non-VIP customer
        Customer regularCustomer = new Customer("Phil", "user@example.com", false);

        //Create a product with category "Shoes"
        Product shoes = new Product("Sneakers", "Shoes", 100.0, 5);

        // Apply discount and assert it's 20% off
        double discountedPrice = discountManager.applyDiscount(regularCustomer, shoes);

        // 20% of 100 = 80.0
        assertEquals(80.0, discountedPrice, 0.01, "Shoes category should get a 20% discount.");
    }

    @Test
    @DisplayName("testNoDiscountApplied")
    void testNoDiscountApplied() {
        // Create a non-VIP customer
        Customer customer = new Customer("Regular Joe", "joe@example.com", false);

        // Create a product in a non-discounted category
        Product product = new Product("Clean Code", "books", 100.0, 5);

        // Assert final price equals original
        double finalPrice = discountManager.applyDiscount(customer, product);
        assertEquals(100.0, finalPrice, 0.01, "Non-VIP customer buying non-discounted category should pay full price.");

    }

    @Test
    @DisplayName("testVipAndCategoryDiscountConflict")
    void testVipAndCategoryDiscountConflict() {
        // ⚠️ This test ensures you understand your business logic:
        // If both VIP and category apply, which one should take priority?
        // Design decision: Apply only one, or the higher?
        // Assert consistent behavior
        Customer vipCustomer = new Customer("Linda", "linda@example.com", true);

        // Create a product in a discounted category (e.g., "shoes" with 20%)
        Product shoes = new Product("Sneakers", "shoes", 100.0, 5);

        // Apply discount
        double finalPrice = discountManager.applyDiscount(vipCustomer, shoes);

        // VIP (10%) + category (20%) = 30% → 100 - 30% = 70.0
        assertEquals(70.0, finalPrice, 0.01, "VIP + category discount should be combined (30% off).");
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        // Reset if needed (usually not necessary for pure logic tests)
        System.out.println("✔\uFE0FTest Passed: 📃 " + testInfo.getDisplayName());
    }
    @AfterAll
    static void afterAllTests() {
        System.out.println("✅  .... ✨ Tests  Successfully Done ✨");
    }
}
