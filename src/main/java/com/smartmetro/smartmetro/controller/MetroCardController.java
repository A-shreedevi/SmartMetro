
package com.smartmetro.smartmetro.controller;

import com.smartmetro.smartmetro.entity.MetroCard;
import com.smartmetro.smartmetro.service.MetroCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class MetroCardController {

    private final MetroCardService metroCardService;

    public MetroCardController(MetroCardService metroCardService) {
        this.metroCardService = metroCardService;
    }

    @PostMapping
    public MetroCard createCard(@RequestBody MetroCard card) {
        return metroCardService.createCard(card);
    }

    @GetMapping
    public List<MetroCard> getAllCards() {
        return metroCardService.getAllCards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetroCard> getCardById(@PathVariable Long id) {
        return metroCardService.getCardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{cardNumber}")
    public ResponseEntity<MetroCard> getCardByNumber(
            @PathVariable String cardNumber) {
        return metroCardService.getCardByNumber(cardNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public MetroCard updateCardStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return metroCardService.updateCardStatus(id, status);
    }

    @PatchMapping("/{id}/block")
    public MetroCard blockCard(@PathVariable Long id) {
        return metroCardService.updateCardStatus(id, "BLOCKED");
    }

    @PatchMapping("/{id}/activate")
    public MetroCard activateCard(@PathVariable Long id) {
        return metroCardService.updateCardStatus(id, "ACTIVE");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        metroCardService.deleteCard(id);
        return ResponseEntity.ok("Metro card deleted successfully");
    }
}
