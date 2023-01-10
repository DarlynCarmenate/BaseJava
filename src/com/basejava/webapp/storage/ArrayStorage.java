package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage implements Storage {
    private final static int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int numResumes;

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("There's no " + resume.getUuid() + " for updating");
        } else {
            System.out.println("Storage " + storage[index].toString() + " updated");
            storage[index] = resume;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, numResumes, null);
        numResumes = 0;
        System.out.println("Storage cleared");
    }


    public void save(Resume resume) {
        if (numResumes > STORAGE_LIMIT) {
            System.out.println("The storage overflow");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("The resume already exists");
        } else {
            storage[numResumes] = resume;
            System.out.println("Resume " + resume + " saved");
            numResumes++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("There's no " + uuid + " to get");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid){
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("There's no " + uuid + " for deleting");
        } else {
            storage[index] = storage[numResumes - 1];
            storage[numResumes - 1] = null;
            System.out.println("Resume " + uuid + " deleted");
            numResumes--;
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

    private int getIndex(String uuid) {
        for (int i = 0; i < numResumes; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}
