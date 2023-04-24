package ru.javawebinar.basejava.model;

import java.util.*;

public class Resume implements Comparable<Resume> {

    private final java.lang.String uuid;

    private final java.lang.String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume() {
        this("");
    }

    public Resume(java.lang.String fullName) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    public Resume(java.lang.String uuid, java.lang.String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullname must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    public java.lang.String getUuid() {
        return uuid;
    }

    public java.lang.String getFullName() {
        return fullName;
    }

    @Override
    public java.lang.String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.getUuid());
    }
}
