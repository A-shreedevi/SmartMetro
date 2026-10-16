package com.smartmetro.smartmetro.service;

import com.smartmetro.smartmetro.entity.Route;
import com.smartmetro.smartmetro.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public Route updateRoute(Long id, Route updatedRoute) {

        Route existingRoute = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        existingRoute.setSourceStation(updatedRoute.getSourceStation());
        existingRoute.setDestinationStation(updatedRoute.getDestinationStation());
        existingRoute.setDistance(updatedRoute.getDistance());
        existingRoute.setEstimatedTime(updatedRoute.getEstimatedTime());

        return routeRepository.save(existingRoute);
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}
