package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void setResume(Resume r, Object searchKey);

    protected abstract void saveResume(Resume r, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

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

    public Resume[] getAll() {
        Comparator<Resume> comparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        getAllSorted().sort(comparator);
        List<Resume> sortedList= new ArrayList<>(getAllSorted());
        sortedList.sort(comparator);
        return sortedList.toArray(new Resume[0]);
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
