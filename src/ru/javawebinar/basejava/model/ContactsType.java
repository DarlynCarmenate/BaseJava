package ru.javawebinar.basejava.model;

public enum ContactsType {
    PHONE ("Телефон"),
    SKYPE ("Скайп"),
    EMAIL ("Почта"),
    LINKEDIN ("Профиль LinkedIn"),
    GITHUB ("Профиль GitHub"),
    STACKOVERFLOW ("Профиль StackOverflow"),
    HOMEPAGE ("Домашняя страница");

    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
