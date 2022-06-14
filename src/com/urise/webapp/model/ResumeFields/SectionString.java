package com.urise.webapp.model.ResumeFields;

import java.util.HashMap;
import java.util.Map;

public class SectionString extends AbstractSection<String> {

    Map<SectionType, String> map = new HashMap<>();

    public void save(SectionType sectionType, String string) {
        map.put(sectionType, string);
    }

    public void getSectionType() {
        for (Map.Entry<SectionType, String> item : map.entrySet()) {
            System.out.println(item.getKey() + ":\n" + item.getValue());
        }
    }
}
