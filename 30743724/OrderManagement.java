package com.retail;
import java.sql.*;
import java.util.Scanner;

public class OrderManagement extends Abstraction {

    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        String productQuery = "SELECT price, stock_quantity FROM Product WHERE product_id = ?";
        String orderQuery = "INSERT INTO OrderTable (customer_id, order_date, status) VALUES (?, NOW(), 'Processing')";
        String orderItemQuery = "INSERT INTO OrderItems (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        String updateStockQuery = "UPDATE Product SET stock_quantity = stock_quantity - ? WHERE product_id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement productStmt = conn.prepareStatement(productQuery);
                 PreparedStatement orderStmt = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement orderItemStmt = conn.prepareStatement(orderItemQuery);
                 PreparedStatement updateStockStmt = conn.prepareStatement(updateStockQuery)) {

                productStmt.setInt(1, productId);
                ResultSet productRs = productStmt.executeQuery();

                if (productRs.next()) {
                    double price = productRs.getDouble("price");
                    int stockQuantity = productRs.getInt("stock_quantity");

                    if (stockQuantity >= quantity) {
                        // Insert order
                        orderStmt.setInt(1, customerId);
                        orderStmt.executeUpdate();
                        ResultSet orderRs = orderStmt.getGeneratedKeys();
                        orderRs.next();
                        int orderId = orderRs.getInt(1);

                        // Insert order item
                        orderItemStmt.setInt(1, orderId);
                        orderItemStmt.setInt(2, productId);
                        orderItemStmt.setInt(3, quantity);
                        orderItemStmt.setDouble(4, price * quantity);
                        orderItemStmt.executeUpdate();

                        // Update product stock
                        updateStockStmt.setInt(1, quantity);
                        updateStockStmt.setInt(2, productId);
                        updateStockStmt.executeUpdate();

                        conn.commit();
                        System.out.println("Order placed successfully.");
                    } else {
                        System.out.println("Not enough stock available.");
                    }
                } else {
                    System.out.println("Product not found.");
                }

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                System.out.println("Failed to place order.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int orderId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new status (Processing, Shipped, Delivered, Cancelled): ");
        String status = scanner.next();

        String query = "UPDATE OrderTable SET status = ? WHERE order_id = ?";
        try {
            executeUpdate(query, stmt -> {
                stmt.setString(1, status);
                stmt.setInt(2, orderId);
            });
            System.out.println("Order status updated successfully.");
        } catch (DatabaseException e) {
            System.out.println("Failed to update order status.");
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int orderId) {
        String query = "UPDATE OrderTable SET status = 'Cancelled' WHERE order_id = ?";
        try {
            executeUpdate(query, stmt -> stmt.setInt(1, orderId));
            System.out.println("Order cancelled successfully.");
        } catch (DatabaseException e) {
            System.out.println("Failed to cancel order.");
            e.printStackTrace();
        }
    }

    @Override
    public void viewAll() {
        String query = "SELECT * FROM OrderTable";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Order List:");
            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Order Date: " + rs.getTimestamp("order_date"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("-------------------------");
            }

        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
    }
}
