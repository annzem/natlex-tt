package com.github.annzem.natlex_rest.service;

import com.github.annzem.natlex_rest.model.Job;
import com.github.annzem.natlex_rest.model.Section;

import java.util.List;

public interface IEService {
    List<Section> parseExelToSections(Job job, int sheetNum);
    void exportSectionsToFile (Job job, List<Section> sections);

}
