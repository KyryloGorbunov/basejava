package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume r, int index) {
        index = Math.abs(index) - 1;
        System.arraycopy(storage, index, storage, index + 1, size);
        storage[index] = r;
    }

    @Override
    protected void deleteResume(String uuid, int index) {
        System.arraycopy(storage, index + 1, storage, index, size);
    }
}
