package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage_2 extends AbstractStorage<String> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        for(Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().getFullName().equals(searchKey)) {
                storage.replace(entry.getValue().getUuid(), resume);
            }
        }
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(String searchKey) {
        for(Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().getFullName().equals(searchKey)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doDelete(String searchKey) {
        for(Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().getFullName().equals(searchKey)) {
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
        list.sort(Comparator.comparing((Resume b) -> b.getFullName()).thenComparing((Resume b) -> b.getUuid()));
        return list;
    }

    @Override
    protected Integer doSize() {
        return storage.size();
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String fullName) {
        return fullName;
    }
}
