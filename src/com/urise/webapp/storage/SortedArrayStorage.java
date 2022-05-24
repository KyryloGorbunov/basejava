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
    protected void insert(Resume r) {
        int index = getIndex(r.getUuid());
        index = Math.abs(index) - 1;
        size++;
        System.arraycopy(storage, index, storage, index + 1, size);
        storage[index] = r;
    }
}
