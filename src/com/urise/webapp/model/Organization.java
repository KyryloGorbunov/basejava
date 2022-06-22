package com.urise.webapp.model;

import com.urise.webapp.util.DateUtil;

import java.util.*;

public class Organization {
    private final Link homepage;
    private final Period period;
    private final Map<Link, Period> periods = new HashMap<>();

    public Organization(String name, String url, DateUtil startDate, DateUtil endDate, String title, String description) {
        this.homepage = new Link(name, url);
        this.period = new Period(startDate, endDate, title, description);
        periods.put(homepage, period);
    }

    public Link getHomepage() {
        return homepage;
    }

    public String getPeriods() {
        return periods.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homepage.equals(that.homepage) && period.equals(that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, period);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homepage=" + homepage +
                ", period=" + period +
                '}';
    }
}

