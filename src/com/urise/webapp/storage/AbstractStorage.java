package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR
            = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void setResume(Resume r, Object searchKey);

    protected abstract void saveResume(Resume r, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> doCopyAll();

    public final void update(Resume r) {
        setResume(r, getExistingSearchKey(r.getUuid()));
        System.out.println("Resume " + r + " successfully updated.");
    }

    public final void save(Resume r) {
        saveResume(r, getNotExistingSearchKey(r.getUuid()));
    }

    public final Resume get(String uuid) {
        return getResume(getExistingSearchKey(uuid));
    }

    public final void delete(String uuid) {
        deleteResume(getExistingSearchKey(uuid));
    }

    public List<Resume> getAllSorted() {
        List<Resume> sortedList = new ArrayList<>(doCopyAll());
        sortedList.sort(RESUME_COMPARATOR);
        return sortedList;
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }


}
