package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

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
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage cleared");
    }


    public void save(Resume resume) {
        if (size > STORAGE_LIMIT) {
            System.out.println("The storage overflow");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("The resume already exists");
        } else {
            storage[size] = resume;
            System.out.println("Resume " + resume + " saved");
            size++;
        }
    }

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

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}
