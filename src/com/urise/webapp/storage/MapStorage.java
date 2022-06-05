package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    HashMap<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void setResume(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        storage.putIfAbsent(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected Resume[] getAllSorted() {
        return storage.values().toArray(new Resume[0]);
    }
}
