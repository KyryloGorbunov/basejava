package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void update(Resume r) {
        getNotExistingSearchKey(r.getUuid());
        setResume(r);
        System.out.println("Resume " + r + " successfully updated.");
    }

    public final void save(Resume r) {
        getExistingSearchKey(r.getUuid());
        saveResume(r);
    }

    public final Resume get(String uuid) {
        getNotExistingSearchKey(uuid);
        return getResume(uuid);
    }

    public final void delete(String uuid) {
        getNotExistingSearchKey(uuid);
        deleteResume(uuid);
    }

    protected abstract int getSearchKey(String uuid);

    protected abstract void setResume(Resume r);

    protected abstract void saveResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);

    protected abstract boolean isExist(String uuid);

    private void getExistingSearchKey(String uuid) {
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
    }

    private void getNotExistingSearchKey(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
    }
}
