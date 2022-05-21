package com.github.annzem.natlex_rest.controller;

import com.github.annzem.natlex_rest.model.GeoClass;
import com.github.annzem.natlex_rest.service.GeoClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("geoClasses")
public class GeoClassController {

    private GeoClassService geoClassService;

    @GetMapping("by-code")
    public ResponseEntity <GeoClass> getGeoClassByCode(@RequestParam String code) {
        Optional<GeoClass> geoClass = geoClassService.findGeoClassByCode(code);

        if (geoClass.isPresent()) {
            return new ResponseEntity(geoClass, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity <List<GeoClass>> getAllGeoClasses() {
        return new ResponseEntity<>(geoClassService.findAllGeoClasses(), HttpStatus.OK);
    }

    @PostMapping ("/")
    public ResponseEntity<GeoClass> saveGeoClass(@RequestBody GeoClass geoClass) {
        return new ResponseEntity(geoClassService.saveGeoClass(geoClass), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<GeoClass> updateGeoClass(@PathVariable long id, @RequestBody GeoClass geoClass) {
        return new ResponseEntity(geoClassService.updateGeoClass(id, geoClass), HttpStatus.OK);
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
