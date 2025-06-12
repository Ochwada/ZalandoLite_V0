package com.zalando.lite;

import com.zalando.lite.customer.Customer;
import com.zalando.lite.managerSystem.ReviewManager;
import com.zalando.lite.products.Product;
import com.zalando.lite.review.Review;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ReviewManager}.
 * <p>
 * These tests verify:
 * - Reviews are properly added and linked to the correct product
 * - Multiple reviews can be added per product
 * - Retrieving reviews by product ID returns expected results
 * - System handles missing reviews safely (returns empty list, not null)
 * <p>
 * Concepts reinforced:
 * - Nested collection testing: Map<Integer, List<Review>>
 * - Defensive testing (null handling)
 * - Composition: testing Product + Customer + Review
 */
public class ReviewManagerTest {

    private ReviewManager reviewManager;
    private Product sampleProduct;
    private Customer sampleCustomer;

    @BeforeEach
    void setUp() {
        // Initialize test manager and sample objects
        reviewManager = new ReviewManager();
        sampleProduct = new Product("Sample Sneakers", "shoes", 49.99, 10);
        sampleCustomer = new Customer();
        //Set minimal required fields like product ID, customer ID
        sampleCustomer.setName("Linda");
    }

    @Test
    void testAddReviewAndRetrieve() {
        // Create a Review and add it
        LocalDateTime now = LocalDateTime.now();// create a timestamp
        Review review = new Review(sampleProduct, 5, sampleCustomer, "Very comfortable!", now); // Create a Review

        reviewManager.addReview(review); // add review to the manager;

        // Retrieve reviews for product and assert it contains the review
        List<Review> reviews = reviewManager.getReviewsForProduct(sampleProduct.getId()); // Retrieve the list of reviews for the product

        // Assertions
        assertNotNull(reviews, "Review list should not be null.");
        assertEquals(1, reviews.size(), "There should be exactly one review.");

        Review retrieved = reviews.get(0);
        assertEquals("Very comfortable!", retrieved.getComment(), "The review comment should match.");
        assertEquals(5, retrieved.getRating(), "The review rating should match.");
        assertEquals(sampleCustomer, retrieved.getCustomer(), "The customer should match.");

        // assertEquals(now, retrieved.getTimestamp(), "The timestamp should match.");
        /** Compare timestamps with toString() or truncation to seconds (not more) e.g,
         * Expected :2025-06-06T11:17:02.300566500
         * Actual   :2025-06-06T11:17:02.308763400
         * to :2025-06-06T11:17:02
         */

        assertEquals(now.truncatedTo(ChronoUnit.SECONDS), retrieved.getTimestamp().truncatedTo(ChronoUnit.SECONDS),
                "The timestamp (to the second) should match.");

    }

    @Test
    void testMultipleReviewsForSameProduct() {
        LocalDateTime now = LocalDateTime.now();

        //Add 2–3 reviews for same product
        Review review1 = new Review(sampleProduct, 5, sampleCustomer, "Excellent!", now);
        Review review2 = new Review(sampleProduct, 4, sampleCustomer, "Very good", now.plusSeconds(1));
        Review review3 = new Review(sampleProduct, 3, sampleCustomer, "Satisfactory", now.plusSeconds(2));

        // Add all reviews to the manager
        reviewManager.addReview(review1);
        reviewManager.addReview(review2);
        reviewManager.addReview(review3);

        // Retrieve list and assert size == 3
        List<Review> reviews = reviewManager.getReviewsForProduct(1000);

        // Assert the list contains all three reviews
        assertNotNull(reviews, "Review list should not be null");
        assertEquals(3, reviews.size(), "Review list should contain exactly 3 entries");

        // Optional: verify comments or order
        assertEquals("Excellent!", reviews.get(0).getComment());
        assertEquals("Very good", reviews.get(1).getComment());
        assertEquals("Satisfactory", reviews.get(2).getComment());

    }

    @Test
    void testRetrieveReviewsForNonReviewedProductReturnsEmptyList() {

        //  Use a product ID with no reviews
        int nonReviewedProductId = 1999;

        // Attempt to retrieve reviews
        List<Review> reviews = reviewManager.getReviewsForProduct(nonReviewedProductId);

        // Assert that result is not null and isEmpty() is true
        // Assert the result is not null and is an empty list
        assertNotNull(reviews, "Review list should not be null for a product with no reviews.");
        assertTrue(reviews.isEmpty(), "Review list should be empty when no reviews exist for the product.");
    }

    @AfterEach
    void tearDown() {
        // Optional: cleanup if static/shared state is used later
    }
}
