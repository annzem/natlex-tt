package com.github.annzem.natlex_rest.controller;

import com.github.annzem.natlex_rest.model.GeoClass;
import com.github.annzem.natlex_rest.model.Section;
import com.github.annzem.natlex_rest.repository.GeoClassRepository;
import com.github.annzem.natlex_rest.repository.SectionRepository;
import com.github.annzem.natlex_rest.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections")
public class SectionController {

    private SectionRepository sectionRepository;
    private GeoClassRepository geoClassRepository;
    private SectionService sectionService;

    @Autowired
    public SectionController(SectionRepository sectionRepository, SectionService sectionService, GeoClassRepository geoClassRepository) {
        this.sectionRepository = sectionRepository;
        this.geoClassRepository = geoClassRepository;
        this.sectionService = sectionService;
    }

    @GetMapping("/by-code")
    public ResponseEntity<List<Section>> readSectionsByGeoCode(@RequestParam String code) {
        return new ResponseEntity<>(sectionService.findSectionsByGeoClassCode(code), HttpStatus.OK);
    }

    @GetMapping("/by-name")
    public ResponseEntity<Section> readSectionsByName(@RequestParam String name) {
        Optional<Section> section = sectionService.findSectionByName(name);

        if (section.isPresent()) {
            return new ResponseEntity(section, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Section>> readAllSections() {
//        GeoClass geo1 = new GeoClass("abc", "AC12");
//        GeoClass geo2 = new GeoClass("bcd", "AC10");
//        GeoClass geo0 = new GeoClass("bch", "AC00");
//        geoClassRepository.saveAllAndFlush(Arrays.asList(geo1, geo2, geo0));
//
//        Section section1 = new Section("section1");
//        section1.getGeoClasses().add(geo1);
//        Section section2 = new Section("section2");
//        section2.getGeoClasses().add(geo2);
//        Section section3 = new Section("section3");
//        section3.getGeoClasses().addAll(Arrays.asList(geo1, geo2));
//        sectionRepository.saveAllAndFlush(Arrays.asList(section1, section2, section3));

        return new ResponseEntity<>(sectionService.findAllSections(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Section> saveSection(@RequestBody Section section) {
        try {
            return new ResponseEntity<>(sectionService.saveSection(section), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Section> updateSection(@PathVariable long id, @RequestBody Section section) {
        try {
            return new ResponseEntity(sectionService.updateSection(id, section), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteSection(@PathVariable long id) {
        try {
            sectionService.deleteSection(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
