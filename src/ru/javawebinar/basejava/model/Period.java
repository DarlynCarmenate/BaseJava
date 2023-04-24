package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final String title;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final String description;

    public Period(String title, LocalDate dateFrom, LocalDate dateTo, String description) {
        this.title = title;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Period{" +
                "title='" + title + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (!Objects.equals(title, period.title)) return false;
        if (!Objects.equals(dateFrom, period.dateFrom)) return false;
        if (!Objects.equals(dateTo, period.dateTo)) return false;
        return Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
