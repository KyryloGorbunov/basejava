package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume r = new Resume(uuid);
        return storage.indexOf(r);
    }

    @Override
    protected void setResume(int index, Resume r) {
        storage.set(getIndex(r.getUuid()), r);
    }

    @Override
    protected void saveResume(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(getIndex(uuid));
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    public Resume[] getAll() {
        Resume[] r = new Resume[storage.size()];
        return (storage.toArray(r));
    }

    @Override
    public int size() {
        return storage.size();
    }
}
