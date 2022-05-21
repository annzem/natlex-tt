package com.github.annzem.natlex_rest;

import com.github.annzem.natlex_rest.model.GeoClass;
import com.github.annzem.natlex_rest.model.Section;
import com.github.annzem.natlex_rest.repository.GeoClassRepository;
import com.github.annzem.natlex_rest.repository.SectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

@SpringBootTest
class NatlexRestApplicationTests {

    @Autowired
    GeoClassRepository geoClassRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Test
    void nullEntity() {
        RuntimeException argIsNullError = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            geoClassRepository.saveAndFlush(new GeoClass(null, null));
        });
    }

//   ---
//    @Test
//    void sameEntity() {
//        geoClassRepository.deleteAll();
//        geoClassRepository.saveAndFlush(new GeoClass("abc", "AC12"));
//
//        RuntimeException agrsAreSameError = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
//            geoClassRepository.saveAndFlush(new GeoClass("abc", "AC10"));
//        });
//    }

    @Test
    void findSectionsByGeoClass() {
        geoClassRepository.deleteAll();
        sectionRepository.deleteAll();

        Section section1 = new Section("section1");
        Section section2 = new Section("section2");
        Section section3 = new Section("section3");
//        sectionRepository.saveAllAndFlush(Arrays.asList(section1, section2, section3));

        GeoClass geo1 = new GeoClass("abc", "AC12");
        GeoClass geo2 = new GeoClass("bcd", "AC10");
        geoClassRepository.saveAndFlush(geo1);
        geoClassRepository.saveAndFlush(geo2);

        section1.getGeoClasses().addAll(Arrays.asList(geo1, geo2));
//        section2.getGeoClasses().add(geo1);
        section3.getGeoClasses().add(geo2);
        sectionRepository.saveAllAndFlush(Arrays.asList(section1, section2, section3));

//        Optional<GeoClass> res = geoClassRepository.findById(geo1.getId());
//        Assertions.assertEquals(geo1, res.get());

//        Optional<Section> res = sectionRepository.findByName("section1");
//        Assertions.assertEquals(section1, res.get());


// ---
        List<Section> res = sectionRepository.findAllByGeoClassCode("AC12");
        Assertions.assertEquals(Arrays.asList((sectionRepository.findByName("section1")).get()), res);
    }


}
