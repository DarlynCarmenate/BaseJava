package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> listStorage = new ArrayList<>();
    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        listStorage.set(searchKey, resume);
    }

    @Override
    protected void doClear() {
        listStorage.clear();
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        listStorage.add(resume);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return listStorage.get(searchKey);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        listStorage.remove((int) searchKey);
    }

    @Override
    protected Resume[] doGetAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    protected Integer doSize() {
        return listStorage.size();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if(uuid.equals(listStorage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
