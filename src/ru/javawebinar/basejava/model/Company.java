package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Company {
    private final String name;
    private final String url;
    private final Period period;

    public Company(String name, String url, Period period) {
        this.period = period;
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!Objects.equals(name, company.name)) return false;
        if (!Objects.equals(url, company.url)) return false;
        return Objects.equals(period, company.period);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", period=" + period +
                '}';
    }
}
