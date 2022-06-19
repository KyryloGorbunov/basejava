package com.urise.webapp.model.ResumeFields;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    private String name;
    private String website;
    public List<Period> periods = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return '\n' + name + ' ' + website + ' ' + periods ;
    }
}
