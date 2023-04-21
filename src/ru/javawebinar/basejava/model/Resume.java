package ru.javawebinar.basejava.model;

import java.util.*;

public class Resume implements Comparable<Resume> {

    private final String uuid;

    private final String fullName;

    private final Map<ContactsType, Contacts> contacts = new EnumMap<>(ContactsType.class);

    private final Map<SectionType, Solution> sections = new EnumMap<>(SectionType.class);

    public Resume() {
        this("");
    }

    public Resume(String fullName) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullname must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Map<ContactsType, Contacts> getContacts() {
        return contacts;
    }

    public Map<SectionType, Solution> getSections() {
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

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.getUuid());
    }
}
