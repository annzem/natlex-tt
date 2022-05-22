package com.github.annzem.natlex_rest.controller;

import com.github.annzem.natlex_rest.model.GeoClass;
import com.github.annzem.natlex_rest.service.GeoClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("geoClasses")
public class GeoClassController {

    private GeoClassService geoClassService;

    @Autowired
    public GeoClassController(GeoClassService geoClassService) {
        this.geoClassService = geoClassService;
    }

    @GetMapping("by-code")
    public ResponseEntity<GeoClass> getGeoClassByCode(@RequestParam String code) {
        Optional<GeoClass> geoClass = geoClassService.findGeoClassByCode(code);

        if (geoClass.isPresent()) {
            return new ResponseEntity(geoClass, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GeoClass>> getAllGeoClasses() {
        return new ResponseEntity<>(geoClassService.findAllGeoClasses(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<GeoClass> saveGeoClass(@RequestBody GeoClass geoClass) {
        try {
            return new ResponseEntity(geoClassService.saveGeoClass(geoClass), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<GeoClass> updateGeoClass(@PathVariable long id, @RequestBody GeoClass geoClass) {
        try {
            return new ResponseEntity<>(geoClassService.updateGeoClass(id, geoClass), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteGeoClass(@PathVariable long id) {
        try {
            geoClassService.deleteGeoClass(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
