package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.ArrayStorage;
import com.basejava.webapp.storage.Storage;

public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        final Resume resume1 = new Resume();
        resume1.setUuid("uuid1");
        final Resume resume2 = new Resume();
        resume2.setUuid("uuid2");
        final Resume resume3 = new Resume();
        resume3.setUuid("uuid3");

        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);
        ARRAY_STORAGE.save(resume3);

        System.out.println("Get resume1: " + ARRAY_STORAGE.get(resume1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(resume2);
        System.out.println("New uuid of resume2: " + resume2.getUuid());

        printAll();
        ARRAY_STORAGE.delete(resume1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
