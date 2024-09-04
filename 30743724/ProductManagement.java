package com.retail;
import java.sql.*;
import java.util.Scanner;

public class ProductManagement extends Abstraction {

    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product stock quantity: ");
        int stockQuantity = scanner.nextInt();

        String query = "INSERT INTO Product (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try {
            executeUpdate(query, stmt -> {
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setDouble(3, price);
                stmt.setInt(4, stockQuantity);
            });
            System.out.println("Product added successfully.");
        } catch (DatabaseException e) {
            System.out.println("Failed to add product.");
            e.printStackTrace();
        }
    }

    @Override
    public void update(int productId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new product stock quantity: ");
        int stockQuantity = scanner.nextInt();

        String query = "UPDATE Product SET name = ?, description = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
        try {
            executeUpdate(query, stmt -> {
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setDouble(3, price);
                stmt.setInt(4, stockQuantity);
                stmt.setInt(5, productId);
            });
            System.out.println("Product updated successfully.");
        } catch (DatabaseException e) {
            System.out.println("Failed to update product.");
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int productId) {
        String query = "DELETE FROM Product WHERE product_id = ?";
        try {
            executeUpdate(query, stmt -> stmt.setInt(1, productId));
            System.out.println("Product removed successfully.");
        } catch (DatabaseException e) {
            System.out.println("Failed to remove product.");
            e.printStackTrace();
        }
    }

    @Override
    public void viewAll() {
        String query = "SELECT * FROM Product";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Product List:");
            while (rs.next()) {
                System.out.println("Product ID: " + rs.getInt("product_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Price: " + rs.getDouble("price"));
                System.out.println("Stock Quantity: " + rs.getInt("stock_quantity"));
                System.out.println("-------------------------");
            }

        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
    }
}
