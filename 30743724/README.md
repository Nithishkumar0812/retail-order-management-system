Online Retail Order Management System
Overview
This is a console-based Java application that simulates an online retail order management system. It allows users to manage products, customers, and orders through a menu-driven interface. The application interacts with a MySQL database using JDBC.

Features
Product Management
Add New Product: Insert new products into the database.
View Product Details: Retrieve and display details of all products.
Update Product Information: Modify existing product details.
Remove Product: Delete products from the database.
Customer Management
Add New Customer: Insert new customers into the database.
View Customer Details: Retrieve and display details of a specific customer.
Update Customer Information: Modify existing customer details.
Remove Customer: Delete customers from the database.
View All Customers: Retrieve and display details of all customers.
Order Management
Place New Order: Create new orders and insert them into the database.
View Order Details: Retrieve and display details of all orders.
Update Order Status: Modify the status of an order (Processing, Shipped, Delivered, Cancelled).
Cancel Order: Mark an order as cancelled.

Database Schema

Product Table

product_id (Primary Key)
name
description
price
stock_quantity

Customer Table

customer_id (Primary Key)
name
email
phone
address

Order Table

order_id (Primary Key)
customer_id (Foreign Key references Customer Table)
order_date
status (Processing, Shipped, Delivered, Cancelled)

OrderItems Table

order_item_id (Primary Key)
order_id (Foreign Key references Order Table)
product_id (Foreign Key references Product Table)
quantity
price

Setup Instructions
Prerequisites
Java Development Kit (JDK) - Version 8 or above
MySQL Server - Version 5.7 or above
MySQL Workbench (optional)
JDBC Driver - MySQL Connector/J

Database Setup
Install MySQL Server and start it.

Open MySQL Workbench or any other MySQL client.

Execute the following SQL commands to create the required database and tables:

CREATE DATABASE retail_order_management;

USE retail_order_management;

CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    price DECIMAL(10, 2),
    stock_quantity INT
);

CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    address TEXT
);

CREATE TABLE OrderTable (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Processing', 'Shipped', 'Delivered', 'Cancelled'),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE OrderItems (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES OrderTable(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

Application Setup
Clone the repository from GitHub:

git clone https://github.com/Nithishkumar0812/retail-order-management-system.git
cd retail-order-management-system
Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

Ensure that the MySQL JDBC Driver is included in the project dependencies.

Update the database connection settings in the DatabaseConnection class:

private static final String URL = "jdbc:mysql://localhost:3306/retail_order_management";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";

Compile and run the application:

javac -d bin src/com/retail/*.java
java -cp bin com.retail.Main

Usage Instructions
Launch the application by running the Main class.
Navigate through the menu using the number keys to select different options.
Follow on-screen instructions to manage products, customers, and orders.
Press the appropriate key to exit the application.

Error Handling
The application handles exceptions related to database connectivity, SQL operations, and input validation. In case of an error, a user-friendly message is displayed.
