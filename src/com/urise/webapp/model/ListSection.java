package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {

    private List<String> strings = new ArrayList<>();

    public ListSection() {
    }

    public ListSection(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    @Override
    public String toString() {
        return strings.toString();
    }
}
