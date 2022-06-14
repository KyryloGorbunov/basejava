package com.urise.webapp.model.ResumeFields;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SectionMap extends AbstractSection<Map> {
    Map<String, String> mapStringExperience = new HashMap<>();
    Map<String, String> mapStringEducation = new HashMap<>();
    Map<SectionType, Map<String, String>> map = new LinkedHashMap<>();

    public void save(SectionType sectionType, String date, String string) {
        if (sectionType == SectionType.EXPERIENCE) {
            mapStringExperience.put(date, string);
            map.put(sectionType, mapStringExperience);
        } else {
            mapStringEducation.put(date, string);
            map.put(sectionType,mapStringEducation);
        }
    }

    public void getSectionType() {
        for (Map.Entry<SectionType, Map<String, String >> item : map.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
    }
}
