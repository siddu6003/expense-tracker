package com.siddu6003.expensetracker.repository;

import com.siddu6003.expensetracker.db.DataBaseConnection;
import com.siddu6003.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    @Autowired
    private DataBaseConnection dataBaseConnection;

    public Long getUserIdByUsername(String username) {
        Long userId = null;
        String query = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getLong("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }


    public int registerUser(String name, String username, String password) {
        String sql = "INSERT INTO users (name, username, password, createDate, updatedDate) VALUES (?, ?, ?, NOW(), NOW())";

        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, password); // hash if needed
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password")); // Should be hashed
                user.setCreateDate(rs.getDate("createDate"));
                user.setUpdatedDate(rs.getDate("updatedDate"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace(); // log it properly in production
        }

        return null;
    }
}
