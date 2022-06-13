package com.urise.webapp.model.ResumeFields;

import java.util.HashMap;
import java.util.Map;

public class SectionString extends AbstractSection<String> {
    private String field;

    public Map<SectionType, String> sectionMap = new HashMap<>();

    public SectionString(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }

}
