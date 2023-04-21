package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.SectionType;

public class TestSingleton {
    /* Это ленивый singleton. акой часто бывает, когда сложный для конструирования объеки. он не кноструируется сразу,
    а просто создается, а в конструкторе идет проверка условия. И только при соблюдении какго-то условия, он
    конструируется. В условиях многопоточности такой singleton - нерабочий вариант. насоздается больше, чем 1
    вариант разными потоками.
     */
    private static TestSingleton instance;

    public static TestSingleton getInstance() {
        //условие в конструкторе, при котором создается singleton
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance.ordinal());
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }
    }

    //Самый простой альтернативный Singleton в java можно сделать с пом enum
    public enum Singleton {
        INSTANCE
    }

}
