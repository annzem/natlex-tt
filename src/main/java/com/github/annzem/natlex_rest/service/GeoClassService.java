package com.github.annzem.natlex_rest.service;

import com.github.annzem.natlex_rest.model.GeoClass;
import com.github.annzem.natlex_rest.repository.GeoClassRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GeoClassService {

    @Autowired
    GeoClassRepository geoClassRepository;

    public GeoClassService(GeoClassRepository geoClassRepository) {
        this.geoClassRepository = geoClassRepository;
    }

    public Optional<GeoClass> findGeoClassByCode(String code) {
        return geoClassRepository.findGeoClassByCode(code);
    }

    public List<GeoClass> findAllGeoClasses () {
        return geoClassRepository.findAll();
    }

    public GeoClass saveGeoClass(GeoClass geoClass) {
        return geoClassRepository.save(geoClass);
    }

    public GeoClass updateGeoClass(Long id, GeoClass newGeoClass) {
        return geoClassRepository.findById(id).map(geoClass -> {
            geoClass.setName(newGeoClass.getName());
            return geoClassRepository.save(geoClass);
        }).orElseThrow(
                () -> new RuntimeException("There is no geoClass with given id")
        );
    }

    public void deleteGeoClass(Long id) {
        geoClassRepository.delete(geoClassRepository.getById(id));
    }
}
