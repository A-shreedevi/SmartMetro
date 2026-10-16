package com.smartmetro.smartmetro.repository;

import com.smartmetro.smartmetro.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}