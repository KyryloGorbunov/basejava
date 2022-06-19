package com.urise.webapp.model.ResumeFields;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends AbstractSection {

    public List<Organization> organizations = new ArrayList<>();

    @Override
    public String toString() {
        return " " + organizations;
    }
}
