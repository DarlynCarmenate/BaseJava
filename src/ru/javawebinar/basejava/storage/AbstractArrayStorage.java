package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public final void save(Resume resume) {
        if (size > STORAGE_LIMIT) {
            System.out.println("The storage overflow");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("The resume already exists");
        } else {
            saveItem(resume);
            System.out.println("Resume " + resume + " saved");
            size++;
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("There's no " + resume.getUuid() + " for updating");
        } else {
            System.out.println("Storage " + storage[index].getUuid() + " updated");
            storage[index] = resume;
        }
    }

    public final void delete(String uuid){
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("There's no " + uuid + " for deleting");
        } else {
            size--;
            deleteItem(index);
            storage[size] = null;
            System.out.println("Resume " + storage[index] + " deleted" );
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("There's no " + uuid + " to get");
            return null;
        } else {
            return storage[index];
        }
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage cleared");
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveItem(Resume resume);

    protected abstract void deleteItem(int index);

}
