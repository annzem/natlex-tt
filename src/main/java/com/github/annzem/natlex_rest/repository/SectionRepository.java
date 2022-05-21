package com.github.annzem.natlex_rest.repository;

import com.github.annzem.natlex_rest.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository <Section, Long>{

    Optional<Section> findByName(String name);

//    @Query(value = "SELECT s.id, s.name FROM section s JOIN geo_class gc " +
//            "on s.id = gc.section_id " +
//            "WHERE gc.code = :code", nativeQuery=true)
//    List<Section> findAllByGeoClassCode (@Param("code") String code);

    @Query(value = "SELECT s.id, s.name FROM section_geo_classes sgc JOIN geo_class gc on sgc.geo_classes_id = gc.id\n" +
            "    JOIN section s on sgc.section_id = s.id\n" +
            "    WHERE gc.code = :code", nativeQuery=true)
    List<Section> findAllByGeoClassCode (@Param("code") String code);

}
