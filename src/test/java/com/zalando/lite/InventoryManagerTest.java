package com.zalando.lite;

import com.zalando.lite.managerSystem.InventoryManager;
import com.zalando.lite.products.Product;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link InventoryManager}.
 * <p>
 * These tests validate:
 * - Adding products to inventory
 * - Finding products by ID
 * - Listing all available products
 * - Reducing stock safely and accurately
 * - Checking product availability
 * <p>
 * Concepts reinforced:
 * - Collection management
 * - State mutation and validation
 * - Defensive programming (e.g., stock can't go negative)
 */
public class InventoryManagerTest {

    private InventoryManager inventoryManager;

    @BeforeEach
    void setUp() {
        // Create a fresh InventoryManager before each test
        inventoryManager = new InventoryManager();
    }

    @Test
    void testAddAndFindProductById() {
        inventoryManager = new InventoryManager();

        // Create a sample product with ID = 1
        Product product = new Product("Headphones", "electronics", 89.99, 10);
        int id = product.getId();

        // Add it to the inventory
        inventoryManager.addProduct(product);

        //  Use findProductById and assert the result is not null
        Product result = inventoryManager.findProductById(id);

        // Assert the product was found
        assertNotNull(result, "Product with ID 1000 should be found.");
        assertEquals("Headphones", result.getName(), "The product name should match.");

    }

    @Test
    void testListAllProductsReturnsCorrectSize() {
        inventoryManager = new InventoryManager();

        //  Add multiple products
        Product p1 = new Product("Shoes", "footwear", 59.99, 10);
        Product p2 = new Product("T-Shirt", "clothes", 19.99, 20);
        Product p3 = new Product("Laptop", "electronics", 899.99, 5);

        inventoryManager.addProduct(p1);
        inventoryManager.addProduct(p2);
        inventoryManager.addProduct(p3);

        //Assert that listAllProducts returns the correct count

        List<Product> allProducts = inventoryManager.listAllProducts();
        assertEquals(3, allProducts.size(), "Inventory should contain exactly 3 products.");
    }

    @Test
    void testReduceStockSuccessfully() {
        inventoryManager = new InventoryManager();

        // Add a product with quantity 10
        Product product = new Product("Backpack", "accessories", 49.99, 10);
        int id = product.getId();
        inventoryManager.addProduct(product);

        // Reduce stock by 3
        inventoryManager.reduceStock(id, 3);

        // Fetch updated product
        Product updatedProduct = inventoryManager.findProductById(id);

        //  Assert new stock is 7
        assertEquals(7, updatedProduct.getStock(), "Stock should be reduced to 7 after reducing by 3.");

    }

    @Test
    void testReduceStockFailsOnInsufficientQuantity() {
        inventoryManager = new InventoryManager();

        // Add a product with stock = 2
        Product product = new Product("Power Bank", "electronics", 29.99, 2);
        int id = product.getId();
        inventoryManager.addProduct(product);

        // Try reducing by 5
        boolean result = inventoryManager.reduceStock(id, 5);

        // Assert reduceStock returns false
        assertFalse(result, "reduceStock should return false when trying to reduce more than available stock.");

        // Optional: check stock is still 2
        Product found = inventoryManager.findProductById(id);
        assertNotNull(found);
        assertEquals(2, found.getStock(), "Stock should remain unchanged after failed reduction.");
    }

    @Test
    void testIsProductAvailableReturnsTrueIfInStock() {

        inventoryManager = new InventoryManager();
        // Add a product with stock > 0
        Product product = new Product("Notebook", "stationery", 3.99, 5);
        int id = product.getId();
        inventoryManager.addProduct(product);

        // Assert isProductAvailable returns true
        boolean available = inventoryManager.isProductAvailable(id);
        assertTrue(available, "Product with stock > 0 should be reported as available.");

    }

    @Test
    void testIsProductAvailableReturnsFalseIfOutOfStock() {
        inventoryManager = new InventoryManager();


        // Add a product with stock = 0
        Product product = new Product("Notebook", "stationery", 3.99, 0);
        int id = product.getId();
        inventoryManager.addProduct(product);

        // Assert isProductAvailable returns false
        boolean available = inventoryManager.isProductAvailable(id);
        assertFalse(available, "Product with stock = 0 should be reported as NOT available.");
    }

    @AfterEach
    void tearDown() {
        // Clean up if necessary (not strictly needed for this manager)
    }
}
