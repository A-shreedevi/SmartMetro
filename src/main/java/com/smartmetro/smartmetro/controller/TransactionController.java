
package com.smartmetro.smartmetro.controller;

import com.smartmetro.smartmetro.entity.Transaction;
import com.smartmetro.smartmetro.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/recharge/{cardId}")
    public Transaction rechargeCard(
            @PathVariable Long cardId,
            @RequestParam double amount) {
        return transactionService.rechargeCard(cardId, amount);
    }

    @PostMapping("/fare/{cardId}")
    public Transaction deductFare(
            @PathVariable Long cardId,
            @RequestParam double fare) {
        return transactionService.deductFare(cardId, fare);
    }


    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/card/{cardId}")
    public List<Transaction> getTransactionsByCardId(
            @PathVariable Long cardId) {
        return transactionService.getTransactionsByCardId(cardId);
    }
}

