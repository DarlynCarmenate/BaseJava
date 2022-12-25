package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int numResumes;

    public void clear() {
        Arrays.fill(storage, 0, numResumes, null);
        numResumes = 0;
    }

    public void save(Resume resume) {
        storage[numResumes] = resume;
        numResumes++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < numResumes; i++) {
            if (uuid.equals(storage[i].toString()))
                return storage[i];
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < numResumes; i++) {
            if (uuid.equals(storage[i].toString())) {
                numResumes--;
                System.arraycopy(storage, i + 1, storage, i, numResumes - i);
                storage[numResumes] = null;
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
}
