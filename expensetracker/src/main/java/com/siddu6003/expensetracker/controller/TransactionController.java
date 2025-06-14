package com.siddu6003.expensetracker.controller;

import com.siddu6003.expensetracker.model.Transaction;
import com.siddu6003.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/addTransaction")
    public String addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @DeleteMapping("/deleteTransaction")
    public String deleteTransaction(@RequestParam("transactionId") Long transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }

    @PatchMapping("/updateTransaction")
    public  String updateTransaction(@RequestBody Transaction transaction) {
        return transactionService.updateTransaction(transaction);
    }

    @GetMapping("/getTransactions")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactionsByUser();
    }
}
