package com.retail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public abstract class Abstraction {
    protected void executeUpdate(String query, QueryParameterSetter setter) throws DatabaseException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setter.setParameters(stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to execute update.", e);
        }
    }

    // Abstract methods for specific operations
    public abstract void add();
    public abstract void update(int id);
    public abstract void remove(int id);
    public abstract void viewAll();
}

@FunctionalInterface
interface QueryParameterSetter {
    void setParameters(PreparedStatement stmt) throws SQLException;
}
