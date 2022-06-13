package com.urise.webapp.model.ResumeFields;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SectionMap extends AbstractSection<Map> {
    Date date;
    String field;

    public SectionMap(Date date, String field) {
        this.date = date;
        this.field = field;
    }

    Map<Date, String> map = new HashMap<>();
}
