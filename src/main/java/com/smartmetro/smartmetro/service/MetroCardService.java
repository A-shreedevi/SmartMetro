
package com.smartmetro.smartmetro.service;

import com.smartmetro.smartmetro.entity.MetroCard;
import com.smartmetro.smartmetro.repository.MetroCardRepository;
import com.smartmetro.smartmetro.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetroCardService {

    private final MetroCardRepository metroCardRepository;
    private final UserRepository userRepository;

    public MetroCardService(
            MetroCardRepository metroCardRepository,
            UserRepository userRepository) {
        this.metroCardRepository = metroCardRepository;
        this.userRepository = userRepository;
    }

    public MetroCard createCard(MetroCard card) {

        if (card.getUser() == null || card.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        Long userId = card.getUser().getId();

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        card.setUser(user);
        card.setId(null);
        card.setStatus("ACTIVE");

        return metroCardRepository.save(card);
    }

    public List<MetroCard> getAllCards() {
        return metroCardRepository.findAll();
    }

    public Optional<MetroCard> getCardById(Long id) {
        return metroCardRepository.findById(id);
    }

    public Optional<MetroCard> getCardByNumber(String cardNumber) {
        return metroCardRepository.findByCardNumber(cardNumber);
    }

    public MetroCard updateCardStatus(Long id, String status) {

        MetroCard card = metroCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Metro card not found"));

        if (!status.equals("ACTIVE") && !status.equals("BLOCKED")) {
            throw new RuntimeException("Status must be ACTIVE or BLOCKED");
        }

        card.setStatus(status);

        return metroCardRepository.save(card);
    }

    public void deleteCard(Long id) {
        if (!metroCardRepository.existsById(id)) {
            throw new RuntimeException("Metro card not found");
        }

        metroCardRepository.deleteById(id);
    }
}
