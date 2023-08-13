package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;
import java.time.Month;

public class ResumeTestData {
    public static Resume getResume(String uuid, String fullName) {
        Resume r = new Resume(uuid, fullName);
        r.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
            r.addContact(ContactType.PHONE, "+7(921) 855-0482");
            r.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения " +
                    "по Java Web и Enterprise технологиям"));
            r.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                    "креативность, инициативность. Пурист кода и архитектуры."));
            r.addSection(SectionType.ACHIEVEMENT, new ListSection("реализация Java проектов", "Разработка Web " +
                    "приложения", "Реализация двухфакторной аутентификации"));
            r.addSection(SectionType.QUALIFICATIONS, new ListSection("Java Frameworks", "SQL", "Python: Django"));
            r.addSection(SectionType.EXPERIENCE,
                    new OrganizationSection(
                            new Organization("Java Online Projects", "http://javaops.ru/",
                                    new Organization.Position(2013, Month.OCTOBER, "автор",
                                            "Создание, организация и проведение Java онлайн проектов и стажировок."))));
            r.addSection(SectionType.EDUCATION,
                    new OrganizationSection(
                            new Organization("Санкт-Петербургский национальный исследовательский университет информационных " +
                                    "технологий, механики и оптики", "http://www.ifmo.ru/",
                                    new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "" +
                                            "Аспирантура (программист С, С++)", null),
                                    new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.JULY,
                                            "Инженер (программист Fortran, C)", null)),
                            new Organization("Organization12", "http://Organization12.ru")));
        return r;
    }
}
