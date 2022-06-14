package com.urise.webapp.model.ResumeFields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionList extends AbstractSection<List> {
    List<String> list = new ArrayList<>();

    Map<SectionType, List<String>> map = new HashMap<>();

    public void save(SectionType sectionType, String string) {
        list.add(string);
        map.put(sectionType, list);
    }

    public void getSectionType() {
        for (Map.Entry<SectionType, List<String>> item : map.entrySet()) {
            System.out.println(item.getKey() + ": ");
            for (String list1: list) {
                System.out.println(list1);
            }
        }
    }
}
