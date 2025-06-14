package com.siddu6003.expensetracker.enums;

public enum TransactionType {

    CREDIT("credit"),
    DEBIT("debit");

    private String transactionType;

    TransactionType(String name) {
        this.transactionType = name;
    }
 }
