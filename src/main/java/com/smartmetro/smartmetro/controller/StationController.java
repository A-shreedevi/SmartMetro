package com.smartmetro.smartmetro.controller;

import com.smartmetro.smartmetro.entity.Station;
import com.smartmetro.smartmetro.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public Station addStation(@RequestBody Station station) {
        return stationService.addStation(station);
    }

    @GetMapping
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable Long id) {

        return stationService.getStationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Station updateStation(
            @PathVariable Long id,
            @RequestBody Station station) {

        return stationService.updateStation(id, station);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStation(@PathVariable Long id) {

        stationService.deleteStation(id);

        return ResponseEntity.ok("Station deleted successfully");
    }
}
