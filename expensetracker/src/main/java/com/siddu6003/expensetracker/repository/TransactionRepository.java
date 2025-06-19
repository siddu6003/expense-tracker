package com.siddu6003.expensetracker.repository;

import com.siddu6003.expensetracker.common.UserContext;
import com.siddu6003.expensetracker.db.DataBaseConnection;
import com.siddu6003.expensetracker.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    @Autowired
    DataBaseConnection dataBaseConnection;

    @Autowired
    UserContext userContext;

    public int insertTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions (user_id, description, amount, category_id, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, userContext.getUserId());
            stmt.setString(2, transaction.getDescription());
            stmt.setBigDecimal(3, transaction.getAmount());
            stmt.setLong(4, 1L);
            stmt.setString(5, "");

            return stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteTransaction(Long transactionId) {
        String query = "DELETE FROM transactions WHERE user_id = ? AND transaction_id = ?";
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, userContext.getUserId());
            stmt.setLong(2, transactionId);

            return stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateTransaction(Transaction transaction) {
        String query = "UPDATE transactions SET description = ?, amount = ?, category_id = ?, type = ?, updated_at = CURRENT_TIMESTAMP WHERE transaction_id = ? AND user_id = ?";
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, transaction.getDescription().toString());
            stmt.setBigDecimal(2, transaction.getAmount());
            stmt.setLong(3, 1L);
            stmt.setString(4, "");
            stmt.setLong(5, transaction.getTransactionId());
            stmt.setLong(6, userContext.getUserId());

            return stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Transaction> getTransactionsByUser() {
        Long userId = userContext.getUserId();
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT transaction_id, user_id, description, amount, category_id, type, created_at, updated_at FROM transactions where user_id = ? ORDER BY transaction_id DESC";

        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getLong("transaction_id"));
                    transaction.setUserId(rs.getLong("user_id"));
                    transaction.setDescription(rs.getString("description"));
                    transaction.setAmount(rs.getBigDecimal("amount"));
                    transaction.setCategoryId(rs.getLong("category_id"));
                    transaction.setCreatedAt(rs.getDate("created_at"));
                    transaction.setUpdatedAt(rs.getDate("updated_at"));

                    transactions.add(transaction);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }

}
