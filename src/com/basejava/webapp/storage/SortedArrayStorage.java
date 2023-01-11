package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("There's no " + resume.getUuid() + " for updating");
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size > STORAGE_LIMIT) {
            System.out.println("The storage overflow");
        } else if (index >= 0) {
            System.out.println("The resume already exists");
        } else {
            int pos = Math.abs(index) - 1;
            System.arraycopy(storage, pos, storage, pos + 1, size - pos);
            storage[pos] = resume;
            System.out.println("Resume " + resume + " saved");
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
