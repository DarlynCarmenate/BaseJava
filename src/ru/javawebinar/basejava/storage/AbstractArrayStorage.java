package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

//    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    public void doSave(Resume resume, Integer searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, searchKey);
            System.out.println("Resume " + resume + " saved");
            size++;
        }
    }

    @Override
    public void doUpdate(Resume resume,Integer searchKey) {
        System.out.println("Storage " + storage[searchKey].getUuid() + " updated");
        storage[searchKey] = resume;
    }

    @Override
    public void doDelete(Integer searchKey){
        size--;
        fillDeletedElement(searchKey);
        storage[size] = null;
        System.out.println("Resume " + storage[searchKey] + " deleted" );
    }

    @Override
    public Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    public void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage cleared");
    }

    @Override
    public List<Resume> doGetAllSorted() {
        List<Resume> list = Arrays.asList(storage);
        list.sort(Comparator.comparing((Resume b) -> b.getFullName()).thenComparing((Resume b) -> b.getUuid()));
        return list;
    }

    @Override
    public Integer doSize() {
        return size;
    }

    @Override
    public boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

}
