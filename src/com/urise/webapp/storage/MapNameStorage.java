package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapNameStorage extends AbstractStorage {

    HashMap<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected String getSearchKey(String fullName) {
        return fullName;
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
        return storage.get((String) searchKey);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> storageList = new ArrayList<>(storage.values());
        return storageList;
    }
}
