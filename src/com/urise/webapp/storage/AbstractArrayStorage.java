package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void setResume(Resume r) {
        int index = getSearchKey(r.getUuid());
        storage[index] = r;
    }

    @Override
    protected void saveResume(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(r, getSearchKey(r.getUuid()));
            size++;
        }
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage[getSearchKey(uuid)];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(String uuid) {
        return getSearchKey(uuid) >= 0;
    }

    protected abstract void insertResume(Resume r, int index);
}
