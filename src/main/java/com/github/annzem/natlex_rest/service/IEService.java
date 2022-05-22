package com.github.annzem.natlex_rest.service;

import com.github.annzem.natlex_rest.model.Job;
import com.github.annzem.natlex_rest.model.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEService {
    List<Section> parseExelToSections(Job job, int sheetNum);
    void exportSectionsToFile (Job job, List<Section> sections);

}
