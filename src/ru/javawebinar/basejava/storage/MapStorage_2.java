package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage_2 extends AbstractStorage<Resume> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, Resume searchKey) {
        for(Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().equals(searchKey)) {
                storage.replace(entry.getValue().getUuid(), resume);
            }
        }
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        for(Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().equals(searchKey)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doDelete(Resume searchKey) {
        for(Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().equals(searchKey)) {
                storage.remove(entry);
                }
            }
        }

    @Override
    protected List<Resume> doGetAllSorted() {
        List<Resume> list = new ArrayList<>();
        storage.entrySet().stream().forEach(entry -> {
            list.add(entry.getValue());
        });
        list.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return list;
    }

    @Override
    protected Integer doSize() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return storage.containsValue(searchKey);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
