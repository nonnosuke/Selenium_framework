# Swag Labs Test Automation Framework

A UI Test Automation Framework for **Swag Labs** built with Java, Selenium WebDriver, JUnit 5, and Page Object Model (POM).

This project demonstrates an end-to-end automation framework including reusable Page Objects, Assertions, Components, Test Data management, and Allure reporting.

The test application is the Sauce Demo website:
https://www.saucedemo.com/
---

# Tech Stack

- Java 21
- Selenium WebDriver
- JUnit 5
- Maven
- Allure Report
- WebDriverManager
- Page Object Model (POM)

---

# Project Structure

```
src
├── main
│   ├── components
│   ├── locators
│   ├── models
│   ├── pages
│   └── utils
│
└── test
    ├── assertions
    ├── tests
    │   ├── checkout
    │   ├── inventory
    │   ├── login
    │   ├── users
    │   └── cart
    └── resources
        └── csv
```

---

# Design Pattern

This project follows the **Page Object Model** architecture.

```
Test
    │
    ▼
Assertions
    │
    ▼
Page
    │
    ▼
Components
    │
    ▼
Locators
```

Responsibilities are clearly separated:

- Tests describe scenarios
- Assertions verify expected behavior
- Pages represent screens
- Components encapsulate reusable UI sections
- Locators store selectors

---

# Test Users

The framework covers all Swag Labs user types.

| User | Purpose |
|-------|----------|
| Standard User | Normal behavior |
| Locked Out User | Login validation |
| Problem User | Incorrect product information |
| Performance Glitch User | Slow loading |
| Error User | Functional defects |
| Visual User | Visual/UI defects |

---

# Test Coverage

## Login

- Successful login
- Locked user validation
- Invalid credentials

## Inventory

- Product list
- Product details
- Product images
- Prices
- Add to Cart

## Cart

- Add product
- Remove product
- Checkout flow

## Checkout

- Validation
- Overview
- Finish order

## Special Users

### Problem User

- Wrong product image
- Wrong product detail
- Wrong description
- Wrong price

### Error User

- Add button does not work
- Remove button behavior
- Missing description
- Checkout validation skipped

### Visual User

- Incorrect inventory image
- Incorrect detail image
- Misaligned Add button
- Right-aligned product title
- Incorrect prices
- Misplaced cart badge
- Rotated menu icon

### Performance Glitch User

- Login response time

---

# Test Data

Test data is managed using CSV files.

```
users.csv
products.csv
```

The framework loads data through a reusable `CsvDataProvider`.

---

# Assertions

Custom Assertion classes are separated from test logic.

Examples:

```
InventoryAssertions
HeaderAssertions
FooterAssertions
CartAssertions
LoginAssertions
CheckoutAssertions
ProductDetailAssertions
```

This keeps test methods concise and improves maintainability.

---

# Reporting

The framework uses **Allure Report**.

Features include:

- Epic
- Feature
- Story
- Display Name
- Failure screenshots

Example:

```
Epic
    Swag Labs

Feature
    Visual User

Story
    Product Images

Test
    Visual user displays an incorrect inventory product image
```

---

# Running Tests

Run all tests:

```bash
mvn clean test
```

Generate Allure results:

```bash
allure serve target/allure-results
```

---

# Framework Features

- Page Object Model
- Component-based design
- Reusable Assertions
- CSV Data Provider
- Dynamic Locators
- Parameterized Tests
- Allure Reporting
- Screenshot on Failure

---

# Author

Created as a portfolio project to demonstrate practical UI automation framework design using Selenium WebDriver.