package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapNameStorage extends AbstractStorage {

    Map<String[], Resume> storage = new TreeMap<>();

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
        storage.put(new String[]{r.getUuid(), r.getFullName()}, r);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        storage.putIfAbsent(new String[]{r.getUuid(), r.getFullName()}, r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((String[]) searchKey);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove((String[]) searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String[]) searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> storageList = new ArrayList<>(storage.values());
        return storageList;
    }
}
