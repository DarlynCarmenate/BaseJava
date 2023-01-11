package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public abstract void update(Resume resume);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage cleared");
    }

    public abstract void save(Resume resume);

    public void delete(String uuid){
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("There's no " + uuid + " for deleting");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            System.out.println("Resume " + uuid + " deleted");
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
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

    protected abstract int getIndex(String uuid);

}
