# Salesforce Automation Framework

A professional-grade Hybrid BDD Framework designed for Salesforce Lightning automation using **Selenium 4**, **Java**, and **Cucumber**.

## 🚀 Key Features
* **Singleton Design Pattern:** Ensures a single WebDriver instance across the execution.
* **Selenium Manager:** Automatic driver management (no manual .exe setup required).
* **Multi-Environment Support:** Dynamic switching between QA and UAT via properties files.
* **Page Object Model (POM):** Decoupled UI elements from test logic for high maintainability.

## 🛠 Tech Stack
* **Language:** Java 17+
* **Automation:** Selenium WebDriver (v4.x)
* **Testing Engine:** TestNG / Cucumber
* **Build Tool:** Maven
* **Design Pattern:** Singleton & Page Factory

## ⚙️ How to Run
To run tests against the **QA** environment:
```bash
mvn test -Denv=qa