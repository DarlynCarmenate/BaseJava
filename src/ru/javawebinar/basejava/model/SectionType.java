package ru.javawebinar.basejava.model;

public enum SectionType {
    PERSONAL ("Личные качества"),
    OBJECTIVE ("Позиция"),
    ACHIEVEMENT ("Достижения"),
    QUALIFICATIONS ("Квалификация"),
    EXPERIENCE ("Опты работы"),
    EDUCATION ("Образование");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
