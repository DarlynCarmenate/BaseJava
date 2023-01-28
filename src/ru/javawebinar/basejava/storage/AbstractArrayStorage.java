package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size > STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exists");
            throw new ExistStorageException(resume.getUuid());
        } else {
            InsertElement(resume, index);
            System.out.println("Resume " + resume + " saved");
            size++;
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " does not exist");
            throw new NotExistStorageException(resume.getUuid());
        } else {
            System.out.println("Storage " + storage[index].getUuid() + " updated");
            storage[index] = resume;
        }
    }

    public final void delete(String uuid){
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist");
            throw new NotExistStorageException(uuid);
        } else {
            size--;
            fillDeletedElement(index);
            storage[size] = null;
            System.out.println("Resume " + storage[index] + " deleted" );
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist");
            throw new NotExistStorageException(uuid);
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

    protected abstract void InsertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

}
