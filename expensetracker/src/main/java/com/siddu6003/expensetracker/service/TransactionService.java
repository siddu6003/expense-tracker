package com.siddu6003.expensetracker.service;

import com.siddu6003.expensetracker.model.Transaction;
import com.siddu6003.expensetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public String addTransaction(Transaction transaction) {
        int result = transactionRepository.insertTransaction(transaction);
        return result != 0 ? "transaction added" : "transaction addition failed";
    }

    public String deleteTransaction(Long transactionId) {
        int result = transactionRepository.deleteTransaction(transactionId);
        return result != 0 ? "transaction deleted" : "failed to delete transaction";
    }

    public String updateTransaction(Transaction transaction) {
        int result = transactionRepository.updateTransaction(transaction);
        return result != 0 ? "transaction updated successfully" : "transaction updation failed";
    }

    public List<Transaction> getTransactionsByUser() {
        return transactionRepository.getTransactionsByUser();
    }

    public Long getTotalAmount() {
        return transactionRepository.getTotalAmount();
    }
}
