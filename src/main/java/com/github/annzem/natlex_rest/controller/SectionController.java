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
public SectionController (SectionRepository sectionRepository, SectionService sectionService, GeoClassRepository geoClassRepository) {
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
 //         то же самое?
          return new ResponseEntity(section, HttpStatus.OK);
//            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Section>> readAllSections() {

        geoClassRepository.deleteAll();
        sectionRepository.deleteAll();
        Section section1 = new Section("section1");
        Section section2 = new Section("section2");
        Section section3 = new Section("section3");
        sectionRepository.saveAllAndFlush(Arrays.asList(section1, section2, section3));

        GeoClass geo1 = new GeoClass("abc", "AC12");
        GeoClass geo2 = new GeoClass("bcd", "AC10");
        geoClassRepository.saveAndFlush(geo1);
        geoClassRepository.saveAndFlush(geo2);
        return new ResponseEntity<>(sectionService.findAllSections(), HttpStatus.OK);
    }

    @PostMapping ("/")
    public ResponseEntity<Section> saveSection (@RequestBody Section section) {
        return new ResponseEntity<>(sectionService.saveSection(section), HttpStatus.OK);
    }
    //нужны ли скобки

    @PutMapping("{id}")
    public ResponseEntity<Section> updateSection (@PathVariable long id, @RequestBody Section section) {
        return new ResponseEntity(sectionService.updateSection(id, section), HttpStatus.OK);
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
