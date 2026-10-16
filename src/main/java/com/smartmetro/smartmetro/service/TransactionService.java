
package com.smartmetro.smartmetro.service;

import com.smartmetro.smartmetro.entity.MetroCard;
import com.smartmetro.smartmetro.entity.Transaction;
import com.smartmetro.smartmetro.repository.MetroCardRepository;
import com.smartmetro.smartmetro.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final MetroCardRepository metroCardRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            MetroCardRepository metroCardRepository) {
        this.transactionRepository = transactionRepository;
        this.metroCardRepository = metroCardRepository;
    }

    @Transactional
    public Transaction rechargeCard(Long cardId, double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Recharge amount must be greater than zero"
            );
        }

        MetroCard card = metroCardRepository.findById(cardId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Metro card not found"
                ));

        if (!"ACTIVE".equals(card.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot recharge a blocked card"
            );
        }

        card.setBalance(card.getBalance() + amount);
        metroCardRepository.save(card);

        Transaction transaction = new Transaction();
        transaction.setMetroCard(card);
        transaction.setAmount(amount);
        transaction.setTransactionType("RECHARGE");
        transaction.setStatus("SUCCESS");

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction deductFare(Long cardId, double fare) {

        if (!Double.isFinite(fare) || fare <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Fare must be greater than zero"
            );
        }

        MetroCard card = metroCardRepository.findById(cardId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Metro card not found"
                ));

        if (!"ACTIVE".equals(card.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot use a blocked card"
            );
        }

        if (card.getBalance() < fare) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insufficient balance"
            );
        }

        card.setBalance(card.getBalance() - fare);
        metroCardRepository.save(card);

        Transaction transaction = new Transaction();
        transaction.setMetroCard(card);
        transaction.setAmount(fare);
        transaction.setTransactionType("FARE_DEDUCTION");
        transaction.setStatus("SUCCESS");

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByCardId(Long cardId) {

        if (!metroCardRepository.existsById(cardId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Metro card not found"
            );
        }

        return transactionRepository.findByMetroCardId(cardId);
    }
}
