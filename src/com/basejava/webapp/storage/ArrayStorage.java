package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int numResumes;

    public void update(String uuid, String newUuid) {
        if (numResumes > 10000)
            System.out.println("The storage overflow");
        if (checkUuid(uuid) == 0) {
            System.out.println("There's no " + uuid + " for updating");
        } else {
            for (int i = 0; i < numResumes; i++) {
                if (uuid.equals(storage[i].toString())) {
                    storage[i].setUuid(newUuid);
                    System.out.println("Storage updated");
                }
            }
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, numResumes, null);
        numResumes = 0;
        System.out.println("Storage cleared");
    }

    public void save(Resume resume) {
        for (int i = 0; i < numResumes; i++) {
            if (resume.equals(storage[i])) {
                System.out.println("The resume already exists");
                break;
            }
        }
        storage[numResumes] = resume;
        System.out.println("Resume " + storage[numResumes].getUuid() + " saved");
        numResumes++;
    }

    public Resume get(String uuid) {
        if (checkUuid(uuid) == 0) {
            System.out.println("There's no " + uuid + " to get");
            return null;
        }
        for (int i = 0; i < numResumes; i++) {
            if (uuid.equals(storage[i].toString()))
                return storage[i];
        }
        return null;
    }

    public void delete(String uuid){
        if (checkUuid(uuid) == 0) {
            System.out.println("There's no " + uuid + " for deleting");
            return;
        }
        for (int i = 0; i < numResumes; i++) {
            if (uuid.equals(storage[i].toString())) {
                storage[i] = storage[numResumes - 1];
                storage[numResumes] = null;
                System.out.println("Resume " + storage[i].getUuid() + " deleted");
                numResumes--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, numResumes);
    }

    public int size() {
        return numResumes;
    }

    private int checkUuid(String uuid) {
        for (int i = 0; i < numResumes; i++) {
            if (uuid.equals(storage[i].toString()))
                return 1;
        }
        return 0;
    }
}
