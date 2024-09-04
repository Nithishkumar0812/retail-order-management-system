package com.retail;

import java.sql.*;
import java.util.Scanner;

public class CustomerManagement {

    public static void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        String query = "INSERT INTO Customer (name, email, phone, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, address);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int customerId = generatedKeys.getInt(1);
                        System.out.println("Customer added successfully with Customer ID: " + customerId);
                    } else {
                        System.out.println("Customer added successfully, but failed to retrieve Customer ID.");
                    }
                }
            } else {
                System.out.println("Failed to add customer.");
            }

        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
    }


    public static void viewCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();

        String query = "SELECT * FROM Customer WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Address: " + rs.getString("address"));
            } else {
                System.out.println("Customer not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer ID to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new customer phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter new customer address: ");
        String address = scanner.nextLine();

        String query = "UPDATE Customer SET name = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, address);
            stmt.setInt(5, customerId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Failed to update customer.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer ID to remove: ");
        int customerId = scanner.nextInt();

        String query = "DELETE FROM Customer WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer removed successfully.");
            } else {
                System.out.println("Failed to remove customer.");
            }

        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }
    }

    public static void viewID() {
        String query = "SELECT customer_id, name FROM Customer";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Customer List:");
            while (rs.next()) {
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
