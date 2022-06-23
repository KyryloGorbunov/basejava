package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final Link homepage;
    private final Periods periods;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.homepage = new Link(name, url);
        this.periods = new Periods(startDate, endDate, title, description);
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
        return homepage.equals(that.homepage) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, periods);
    }

    @Override
    public String toString() {
        return "\n" + homepage +
                "\n" + periods;
    }
}

