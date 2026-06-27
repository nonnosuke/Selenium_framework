# Selenium Page Object Model Test Framework

## Overview

This project is a UI automation testing framework built with Java, Selenium WebDriver, JUnit 5, and Maven.

The framework follows the **Page Object Model (POM)** design pattern to improve readability, maintainability, and reusability of test code.

The test application is the Sauce Demo website:
https://www.saucedemo.com/

---

## Tech Stack

* Java 17
* Selenium WebDriver
* JUnit 5
* Maven
* ChromeDriver / FirefoxDriver

---

## Project Structure

```
src
├── main
│   ├── java
│   │   ├── base
│   │   ├── models
│   │   ├── pages
│   │   └── utils
│   └── resources
│       └── config.properties
│
└── test
    └── java
        └── tests
```

### base

Contains the BasePage class that provides reusable Selenium methods such as:

* click()
* type()
* visible()
* clickable()
* textOf()

---

### pages

Each page of the application is represented by its own Page Object.

* LoginPage
* InventoryPage
* CartPage
* CheckoutPage
* CheckoutOverviewPage
* CompleteOrderPage

Each page encapsulates its own locators and actions.

---

### models

Contains model classes used by the framework.

Example:

* CartItem

---

### utils

Contains utility classes.

* ConfigReader
* DriverFactory

---

## Features

### Login

* Valid Login
* Invalid Login

### Inventory

* Add product to cart
* Open cart
* Verify cart badge

### Cart

* Verify product exists
* Remove product
* Continue shopping
* Checkout

### Checkout

* Enter customer information
* Required field validation
* Cancel checkout

### Checkout Overview

* Verify cart items
* Finish order
* Cancel order

### Complete Order

* Verify completion message
* Return to inventory page

---

## Design Pattern

This framework follows the Page Object Model (POM).

Each page:

* owns its locators
* exposes business actions
* returns the next page object after navigation

Example:

```
LoginPage
      ↓
InventoryPage
      ↓
CartPage
      ↓
CheckoutPage
      ↓
CheckoutOverviewPage
      ↓
CompleteOrderPage
```

---

## Configuration

Application settings are stored in:

```
src/main/resources/config.properties
```

Example:

```
browser=chrome
headless=false

base.url=https://www.saucedemo.com/

timeout.seconds=10

valid.username=standard_user
valid.password=secret_sauce
```

---

## Running Tests

Run all tests

```
mvn clean test
```

Run a specific test

```
mvn test -Dtest=LoginTest
```

---

## Future Improvements

* Explicit logging
* Screenshot on failure
* Test data management
* Parallel execution
* CI/CD with GitHub Actions
* Reporting (Allure / Extent Report)

---

Automation Test Framework Practice Project
