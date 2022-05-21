package com.github.annzem.natlex_rest.repository;

import com.github.annzem.natlex_rest.model.GeoClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeoClassRepository extends JpaRepository <GeoClass, Long> {
    Optional<GeoClass> findGeoClassByCode(String geoCode);
    Optional<GeoClass> findById (Long id);
}
