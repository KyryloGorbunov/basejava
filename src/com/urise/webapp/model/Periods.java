package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Periods {

    private final Period period;
    private final List<Period> periods = new ArrayList<>();

    public Periods(LocalDate startDate, LocalDate endDate, String position, String description) {
        this.period = new Period(startDate, endDate, position, description);
        periods.add(period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periods periods1 = (Periods) o;
        return Objects.equals(period, periods1.period) && Objects.equals(periods, periods1.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(period, periods);
    }

    @Override
    public String toString() {
        return "" + periods;
    }
}
