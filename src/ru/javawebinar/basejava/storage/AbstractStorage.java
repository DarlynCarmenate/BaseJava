package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    @Override
    public void update(Resume resume) {
        SK searchKey = ifKeyNotExist(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public void clear() {
        System.out.println("Clearing the storage");
        doClear();
    }

    @Override
    public void save(Resume resume) {
        SK searchKey = ifKeyExist(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = ifKeyNotExist(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = ifKeyNotExist(uuid);
        doDelete(searchKey);
    }

//
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = doCopyAll();
        Collections.sort(sortedList, RESUME_COMPARATOR);
        return sortedList;
    }

    @Override
    public int size() {
        return doSize();
    }

    private SK ifKeyNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK ifKeyExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
    protected abstract boolean isExist(SK searchKey);
    protected abstract SK getSearchKey(String uuid);

    protected abstract void doUpdate(Resume resume, SK searchKey);
    protected abstract void doClear();
    protected abstract void doSave(Resume resume, SK searchKey);
    protected abstract Resume doGet(SK searchKey);
    protected abstract void doDelete(SK searchKey);
    protected abstract Integer doSize();
    protected abstract List<Resume> doCopyAll();
}
