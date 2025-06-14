package com.siddu6003.expensetracker.model;


import com.siddu6003.expensetracker.enums.TransactionType;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Timestamp;

public class Transaction {

    private Long transactionId;
    private Long userId;
    private String description;
    private BigDecimal amount;
    private Long categoryId;
    private TransactionType type; // CREDIT or DEBIT
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructors
    public Transaction() {
    }

    public Transaction(Long transactionId, Long userId, String description, BigDecimal amount,
                       Long categoryId, TransactionType type, Timestamp createdAt, Timestamp updatedAt) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.categoryId = categoryId;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters & Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
