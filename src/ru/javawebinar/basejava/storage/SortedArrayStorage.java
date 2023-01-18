package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveItem(Resume resume) {
        int pos = Math.abs(getIndex(resume.getUuid())) - 1;
        System.arraycopy(storage, pos, storage, pos + 1, size - pos);
        storage[pos] = resume;
    }

    @Override
    protected void deleteItem(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }
}
