package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {
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

    @Override
    public Resume[] getAll() {
        return doGetAll();
    }

    @Override
    public int size() {
        return doSize();
    }

    private SK ifKeyNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException("Resume does not exist");
        }
        return searchKey;
    }

    private SK ifKeyExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException("Resume already exists");
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
    protected abstract Resume[] doGetAll();
    protected abstract Integer doSize();
}
