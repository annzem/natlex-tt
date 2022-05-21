package com.github.annzem.natlex_rest.service;

import com.github.annzem.natlex_rest.model.Section;
import com.github.annzem.natlex_rest.repository.GeoClassRepository;
import com.github.annzem.natlex_rest.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository, GeoClassRepository geoClassRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> findSectionsByGeoClassCode(String code) {
        return sectionRepository.findAllByGeoClassCode(code);
    }

    public Optional<Section> findSectionByName(String name) {
        return sectionRepository.findByName(name);
    }

    public List<Section> findAllSections() {
        return sectionRepository.findAll();
    }

    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }
    //нкжно ли тут каждому геоклассу менять секцию?

    public Section updateSection(Long id, Section newSection) {
        return sectionRepository.findById(id).map(section -> {
            section.setName(newSection.getName());
            return sectionRepository.save(section);
        }).orElseThrow(
                () -> new RuntimeException("There is no section with given id")
        );
    }

    public void deleteSection(Long id) {
        sectionRepository.delete(sectionRepository.getById(id));
    }
}
