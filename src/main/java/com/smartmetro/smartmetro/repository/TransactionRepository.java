
package com.smartmetro.smartmetro.repository;

import com.smartmetro.smartmetro.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByMetroCardId(Long cardId);
}
