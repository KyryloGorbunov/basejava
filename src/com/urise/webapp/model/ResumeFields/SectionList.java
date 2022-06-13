package com.urise.webapp.model.ResumeFields;

import java.util.ArrayList;
import java.util.List;

public class SectionList extends AbstractSection<List> {
    String string;

    public SectionList(String string, List<String> list) {
        this.string = string;
        this.list = list;
    }

    List<String> list = new ArrayList<>();

}
