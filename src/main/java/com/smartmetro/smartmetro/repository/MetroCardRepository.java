
package com.smartmetro.smartmetro.repository;

import com.smartmetro.smartmetro.entity.MetroCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetroCardRepository extends JpaRepository<MetroCard, Long> {

    Optional<MetroCard> findByCardNumber(String cardNumber);

}

