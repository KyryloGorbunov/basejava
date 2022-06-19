package com.urise.webapp.model;

import java.util.Objects;

public class Period {

    private String date;
    private String position;
    private String description;

    public Period() {
    }

    public Period(String date, String position, String description) {
        this.date = date;
        this.position = position;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return '\n' + date + '\n' + position + '\n' + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(date, period.date) && Objects.equals(position, period.position) &&
                Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, position, description);
    }
}
