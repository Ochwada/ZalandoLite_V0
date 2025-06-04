# 🛍️ **ZalandoLite – A 2-Day Java Challenge**

**With Solution Milestones and Algorithmic Hints**

Welcome, Java Adventurer! 👋
You're about to build the **ZalandoLite backend**, and unlike most tutorials — we're not giving you the full answer.

You’ll be guided with **goalposts, hints, and thinking prompts**. You’ll connect the dots, make decisions, and build like a real developer.

This is **your system**. And it starts now. 🛠️

---

## 🗓️ 2-Day Challenge Roadmap

| Day   | Focus                                                        |
| ----- | ------------------------------------------------------------ |
| Day 1 | Build the **foundation**: domain models + managers           |
| Day 2 | Layer in **logic**, **reflection**, **I/O**, and **testing** |

---

## 🚀 **Day 1 – Core Building Blocks**

---

### 🔹 Milestone 1: Representing a Product

🧩 **Goal**: Model a fashion product.

📍 Do this in: `src/main/java/com/zalando/lite/Product.java`

**You Need**:

* An ID, name, category, price, stock
* A list of available sizes

**Challenge**:

* What type would best represent sizes like "S", "M", "L"?
* Use encapsulation: how would a setter or getter look?

🧠 *Concepts*: POJO, Encapsulation, `List<String>`, Constructor Patterns

> The term "POJO" initially denoted a Java object which does not follow any of the major Java object models, conventions,
> or frameworks. It has since gained adoption as a language-agnostic term, because of the need for a common and easily
> understood term that contrasts with complicated object frameworks.

**Why it matters**: Products are the stars of your store. Get this right, and everything else becomes easier.

💬 Hint :
Since the recipe doesn't say how many units per potion...
🎩 You decide!
Try making it a method parameter — so the wizard can brew small or large batches.



---

### 🔹 Milestone 2: Managing Inventory

🧩 **Goal**: Create a class to manage a collection of products.

📍 Do this in: `src/main/java/com/zalando/lite/InventoryManager.java`

**You Need**:

- Store products using a `List<Product>`
- Methods:
    - `addProduct(Product p)`
    - `findProductById(int id)`
    - `listAllProducts()`

**Challenge**:

* Which Java collection suits this use case?
* What’s the time complexity of your `findById()`?

🧠 *Concepts*: `List<Product>`, Collections, Iteration, Linear Search

**Why it matters**: Every real store has an inventory system. You’re building one now.

---

### 🔹 Milestone 3: Customer Identity

🧩 **Goal**: Represent a customer of the store.

📍 Do this in: `src/main/java/com/zalando/lite/Customer.java`, `VIP.java`

**You Need**:

- Fields: `int id`, `String name`, `String email`, `List<String> favoriteCategories`, `boolean isVip`
- Annotate the VIP field with `@VIP`

**Challenge**:

* You’ll later use reflection on a `@VIP` annotation — how might you apply that here?
* How would you represent a customer's favorite product types?

🧠 *Concepts*: Annotations, `List<String>`, Custom Metadata, Encapsulation

**Why it matters**: We’ll use this structure for discounts and personalization.

---

### 🔹 Milestone 4: Customer Management

🧩 **Goal**: Build a way to register and retrieve customers quickly.

📍 Do this in: `src/main/java/com/zalando/lite/CustomerManager.java`

**You Need**:

- Store customers using a `Map<Integer, Customer>`
- Methods:
    - `registerCustomer(Customer c)`
    - `getCustomerById(int id)`

**Challenge**:

* Which collection type offers the fastest access by ID?
* Can you think of a way to prevent `NullPointerException` here?

🧠 *Concepts*: `Map<Integer, Customer>`, Null Safety

**Why it matters**: You’ll use this for orders, reviews, and personalization later.

---

### 🔹 Milestone 5: Orders and Line Items

🧩 **Goal**: Model how customers order products.

📍 Do this in: `src/main/java/com/zalando/lite/OrderItem.java`, `Order.java`

**You Need**:

- `OrderItem` = product + quantity
- `Order` = order ID, customer ID, timestamp, and list of items

**Challenge**:

* Should orders store full customer details or just a reference?
* What date/time class would be most modern here?

🧠 *Concepts*: Object Composition, `LocalDateTime`, Clean Modeling

- [How to work with LocalDate or LocalDateTime](https://medium.com/aoftionstyle/how-to-work-with-localdate-or-localdatetime-4ebecc915607)

**Why it matters**: This connects everything — customers, products, and delivery.

---

### 🔹 Milestone 6: Order Management

🧩 **Goal**: Create and retrieve orders for a customer.

📍 Do this in: `src/main/java/com/zalando/lite/OrderManager.java`

**You Need**:

* Check stock before completing an order
* Subtract quantities from inventory
* Retrieve all orders for a customer

**What to build**:
- Methods:
    - `createOrder(int customerId, List<OrderItem> items)`
    - `getOrdersByCustomer(int customerId)`

**Challenge**:

* Where should stock validation happen: inside or outside the manager?
* How would you deal with out-of-stock situations?

🧠 *Concepts*: Defensive Programming, Aggregation, Control Flow

**Why it matters**: This is the transaction engine of your shop.

---

## ⚡ Day 2 – Add Power and Behavior

---

### 🔹 Milestone 7: Courier Modeling

🧩 **Goal**: Represent someone who delivers orders.

📍 Do this in: `src/main/java/com/zalando/lite/Courier.java`

**You Need**:

* Courier details
* A way to toggle availability

**What to build**:
- Fields: `id`, `name`, `vehicleType`, `isAvailable`
- Toggle availability

**Challenge**:

* Think of availability as a state. How would a toggle method look?

🧠 *Concepts*: Boolean State, Simple Modeling

---

### 🔹 Milestone 8: Delivery Management

🧩 **Goal**: Simulate delivery behavior

📍 Do this in: `src/main/java/com/zalando/lite/Delivery.java`, `DeliveryService.java`

**You Need**:

* Link a delivery to a courier and an order
* Assign couriers only if available

**What to build**:
- `assignCourierToOrder(Order order)`
- `updateDeliveryStatus(Delivery d, String newStatus)`

**Challenge**:

* What if all couriers are busy? Do you assign `null` or wait?
* How would you store status updates: enum or strings?

🧠 *Concepts*:  Workflow Logic, State Management

---

### 🔹 Milestone 9: Product Reviews

🧩 **Goal**: Let customers leave and view reviews.

📍 Do this in: `src/main/java/com/zalando/lite/Review.java`, `ReviewManager.java`

**You Need**:

* A review class
* A way to map product IDs to their reviews

**What to build**:
- Add reviews to a map: `Map<Integer, List<Review>>`
- Fetch reviews for a product

**Challenge**:

* Think about how to store multiple reviews per product efficiently.
* How would you prevent `NullPointerException` when retrieving?

🧠 *Concepts*: `Map<Integer, List<Review>>`, Nested Collections

---

### 🔹 Milestone 🔟: Discounts and Reflections

🧩 **Goal**: Apply discounts based on VIP status or category; Give VIPs and shoe buyers a treat!

📍 Do this in: `src/main/java/com/zalando/lite/DiscountManager.java`

**You Need**:

* Logic for VIP: 10% off
* Logic for shoes: 20% off

**What to build**:
- Use reflection to detect VIPs
- Apply 10% off for VIPs, 20% off for "Shoes"

**Challenge**:

* Can you detect `@VIP` using reflection?
* How would you write a method that dynamically applies rules?

🧠 *Concepts*: Reflection, Annotations, Conditional Logic

---

### 🔹 Milestone 1️⃣1️⃣: Delivery Report Export

🧩 **Goal**: Write delivery summaries to a file.

📍 Do this in: `src/main/java/com/zalando/lite/ReportManager.java`

**You Need**:

* Create a text file
* Write delivery details line by line

**What to build**:
- `writeToFile(List<Delivery> deliveries)`

**Challenge**:

* How do you handle file exceptions gracefully?
* What’s the best Java feature for managing file resources?

🧠 *Concepts*: Try-With-Resources, Java IO

---

### 🔹 Milestone 1️⃣2️⃣: Interactive Console App

🧩 **Goal**: Build a simple command-line interface.

📍 Do this in: `src/main/java/com/zalando/lite/Main.java`

**You Need**:

* Menu system with options
* `Scanner` for input

**What to build**:
- Use `Scanner` to select features (e.g., 1: Create Order, 2: Show Reviews)

**Challenge**:

* How do you keep your code clean when handling 5+ menu options?
* How can you reuse manager classes for each option?

🧠 *Concepts*: Input Handling, Loops, Reusability, Separation of Concerns

---

### 🔹 Milestone 1️⃣3️⃣: Unit Tests

🧩 **Goal**: Write test cases for the system's core logic.

📍 Do this in: `src/test/java/com/zalando/lite/YourTestClasses.java`

**You Need**:

* Setup and teardown
* Assertions

**What to build**:
- Test: Product, InventoryManager, CustomerManager, OrderManager, DeliveryService

**Challenge**:

* What’s the difference between `@BeforeEach` and `@BeforeAll`?
* What happens when an assertion fails?

🧠 *Concepts*:  JUnit 5, Test Lifecycle, Assertions

---

## 🎓 Final Review: Connect the Concepts

| Concept                  | You Practiced In           |
| ------------------------ | -------------------------- |
| OOP & Modeling           | Product, Customer, Order   |
| Collections              | Product lists, Review maps |
| Reflection & Annotations | VIP discounts              |
| Exception Handling       | Stock validation, File I/O |
| File Writing             | Report export              |
| Testing                  | Unit tests for managers    |
| System Design Thinking   | Every single milestone!    |

---

### 🔥 Bonus Challenge (Optional – Push Yourself Further!)

You’ve built the essentials. Ready to level up? Try these **extra challenges** using the concepts you’ve already learned:

---

#### 🧮 **Bonus 1: Calculate Order Summaries with Math Functions**

📍 Do this in: `OrderManager.java`

> 🧩 *Use your `OrderManager` to calculate total revenue, average order value, and highest-value order.*

**What**: Add methods for total revenue, average order value, highest order

✅ Concepts: Math Operations, Functions with Return Values, Collections, Iteration

---

#### 🔁 **Bonus 2: Build a Polymorphic Discount System**

📍 Do this in: `Discount.java`, `VipDiscount.java`, `CategoryDiscount.java`

> 🧩 *Create an abstract `Discount` class with different discount strategies as subclasses (e.g., `VipDiscount`, `CategoryDiscount`). Use polymorphism to apply the correct one.*

**What**: Use abstract classes and inheritance

✅ Concepts: Inheritance, Abstract Classes, Polymorphism

---

#### 🔄 **Bonus 3: Add an Undo Feature Using Stack**

📍 Do this in: `ActionManager.java`

> 🧩 *Track recent operations (e.g., product added, order created) using a `Stack`. Let the user undo the last action.*

**What**: Use `Stack<String>` to track and undo last action

✅ Concepts: Stack, Control Flow, Object References

---

#### 🧵 **Bonus 4: Simulate Parallel Deliveries Using Threads**

📍 Do this in: `DeliveryThread.java`

> 🧩 *Assign deliveries to couriers and simulate each delivery happening in parallel using Java Threads.*

**What**: Simulate delivery threads

✅ Concepts: Java Threads (Basics), Concurrency, Runnable Interface

---

#### 📦 **Bonus 5: Use Generics for a Unified Manager**

📍 Do this in: `EntityManager<T>.java`

> 🧩 *Can you build a generic `EntityManager<T>` class that handles any type: Product, Customer, Order?*

**What**: A generic class that manages any entity

✅ Concepts: Generics, Reusability, DRY Principle

---

#### 📁 **Bonus 6: Load Orders from a File (Advanced I/O)**

📍 Do this in: `FileOrderLoader.java`

> 🧩 *Instead of just exporting, read a file like `orders.csv` and convert each line into an `Order` object.*

**What**: Read CSV and convert to Order objects

### Watch the below youtube video ;) 
[Reading and parsing delimited CSV data from a file into an Object in Java](https://youtu.be/VX9CwPn-BBE?si=h76_4-K6h8jG5nzV)

✅ Concepts: Java IO, File Reading, String Parsing, Collections


---
You made it through the roadmap. And now…
You’re not just coding. You’re **engineering**.
🧠 Keep building, keep thinking, and remember:

> “Clarity comes from doing.”

🧙‍♂️ — Your Java Mentor _@Safwan_

---
