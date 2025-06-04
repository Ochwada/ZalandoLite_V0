# ZalandoLite

This is a **ZalandoLite backend** - a mini project during the  "Java Backend Engineering - 
Future Women" program! at StartSteps.

---

## 🗓️ 2-Day Roadmap

| Day   | Focus                                                        |
| ----- | ------------------------------------------------------------ |
| Day 1 | Build the **foundation**: domain models + managers           |
| Day 2 | Layer in **logic**, **reflection**, **I/O**, and **testing** |

---

## ⚡ Day 1 – Core Building Blocks

####  🔹 Milestone 1: Representing a Product
 **Goal**: Modeling a fashion product.

---
####  🔹 Milestone 2: Managing Inventory
 **Goal**: A class to manage a collection of products.

---
####  🔹 Milestone 3: Customer Identity
**Goal**: Representing a customer of the store.

---
####  🔹 Milestone 4: Customer Management
 **Goal**: Building a way to register and retrieve customers quickly.

---
####  🔹 Milestone 5: Orders and Line Items
 **Goal**: Modeling how customers order products.

---
####  🔹 Milestone 6: Order Management
 **Goal**: Create and retrieve orders for a customer.

---

## ⚡ Day 2 – Add Power and Behavior

---
####  🔹 Milestone 7: Courier Modeling
 **Goal**: Represent someone who delivers orders.

---
#### 🔹 Milestone 8: Delivery Management
**Goal**: Simulate delivery behavior

---
#### 🔹 Milestone 9: Product Reviews
 **Goal**: Let customers leave and view reviews.

---
#### 🔹 Milestone 10: Discounts and Reflections
 **Goal**: Apply discounts based on VIP status or category; Give VIPs and shoe buyers a treat!

---
#### 🔹 Milestone 11: Delivery Report Export
 **Goal**: Write delivery summaries to a file.

---
#### 🔹 Milestone 12: Interactive Console App
 **Goal**: Build a simple command-line interface.

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

### 🔥 Bonus Challenge 

You’ve built the essentials. Ready to level up? Try these **extra challenges** using the concepts you’ve already learned:

---

####  **Bonus 1: Calculate Order Summaries with Math Functions**

 Do this in: `OrderManager.java`

>  *Use your `OrderManager` to calculate total revenue, average order value, and highest-value order.*

**What**: Add methods for total revenue, average order value, highest order

✅ Concepts: Math Operations, Functions with Return Values, Collections, Iteration

---

####  **Bonus 2: Build a Polymorphic Discount System**

 Do this in: `Discount.java`, `VipDiscount.java`, `CategoryDiscount.java`

>  *Create an abstract `Discount` class with different discount strategies as subclasses (e.g., `VipDiscount`, `CategoryDiscount`). Use polymorphism to apply the correct one.*

**What**: Use abstract classes and inheritance

✅ Concepts: Inheritance, Abstract Classes, Polymorphism

---

####  **Bonus 3: Add an Undo Feature Using Stack**

 Do this in: `ActionManager.java`

>  *Track recent operations (e.g., product added, order created) using a `Stack`. Let the user undo the last action.*

**What**: Use `Stack<String>` to track and undo last action

✅ Concepts: Stack, Control Flow, Object References

---

####  **Bonus 4: Simulate Parallel Deliveries Using Threads**

 Do this in: `DeliveryThread.java`

>  *Assign deliveries to couriers and simulate each delivery happening in parallel using Java Threads.*

**What**: Simulate delivery threads

✅ Concepts: Java Threads (Basics), Concurrency, Runnable Interface

---

####  **Bonus 5: Use Generics for a Unified Manager**

 Do this in: `EntityManager<T>.java`

>  *Can you build a generic `EntityManager<T>` class that handles any type: Product, Customer, Order?*

**What**: A generic class that manages any entity

✅ Concepts: Generics, Reusability, DRY Principle

---

####  **Bonus 6: Load Orders from a File (Advanced I/O)**

 Do this in: `FileOrderLoader.java`

>  *Instead of just exporting, read a file like `orders.csv` and convert each line into an `Order` object.*

**What**: Read CSV and convert to Order objects

### Watch the below youtube video ;)
[Reading and parsing delimited CSV data from a file into an Object in Java](https://youtu.be/VX9CwPn-BBE?si=h76_4-K6h8jG5nzV)

✅ Concepts: Java IO, File Reading, String Parsing, Collections


---
