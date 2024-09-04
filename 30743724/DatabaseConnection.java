package com.retail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/retail_order_management";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws DatabaseException {
        Connection connection = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("MySQL JDBC Driver not found. Include the JDBC jar in your classpath.", e);
        } catch (SQLException e) {
            throw new DatabaseException("Connection to the database failed.", e);
        }
        return connection;
    }
}

