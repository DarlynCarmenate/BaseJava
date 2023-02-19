package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {

    Map<String, Resume> mapStorage = new HashMap<String, Resume>();

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        mapStorage.put(searchKey, resume);
    }

    @Override
    protected void doClear() {
        mapStorage.clear();
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        System.out.println("Put searchkey" + searchKey);
        mapStorage.put(searchKey, resume);
        System.out.println("get SK" + mapStorage.entrySet().toString());
    }

    @Override
    protected Resume doGet(String searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    protected Resume[] doGetAll() {
//        Resume[] resumes;
//        for (Map.Entry<String, Resume> entry : mapStorage.entrySet()) {
//            resumes.
//        }
         return mapStorage.values().toArray(new Resume[0]);
    }

    @Override
    protected Integer doSize() {
        return mapStorage.size();
    }

    @Override
    protected boolean isExist(String searchKey) {
        return searchKey != null;
    }

    @Override
    protected String getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : mapStorage.entrySet()) {
            String key = entry.getKey();
            if (uuid.equals(key)) {
                return key;
            }
        }
        return null;
    }
}
