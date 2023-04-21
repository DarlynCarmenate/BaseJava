package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Company {
    private final String name;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final String url;
    private final String text;

    public Company(String name, LocalDate dateFrom, LocalDate dateTo, String url, String text) {
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.url = url;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "name='" + name + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", position='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company companies = (Company) o;

        if (!Objects.equals(name, companies.name)) return false;
        if (!Objects.equals(dateFrom, companies.dateFrom)) return false;
        if (!Objects.equals(dateTo, companies.dateTo)) return false;
        if (!Objects.equals(url, companies.url)) return false;
        return Objects.equals(text, companies.text);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
