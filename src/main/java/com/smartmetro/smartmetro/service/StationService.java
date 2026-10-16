package com.smartmetro.smartmetro.service;

import com.smartmetro.smartmetro.entity.Station;
import com.smartmetro.smartmetro.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Station addStation(Station station) {
        return stationRepository.save(station);
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    public Station updateStation(Long id, Station updatedStation) {

        Station existingStation = stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));

        existingStation.setName(updatedStation.getName());
        existingStation.setLocation(updatedStation.getLocation());
        existingStation.setLine(updatedStation.getLine());
        existingStation.setActive(updatedStation.isActive());

        return stationRepository.save(existingStation);
    }

    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }
}
