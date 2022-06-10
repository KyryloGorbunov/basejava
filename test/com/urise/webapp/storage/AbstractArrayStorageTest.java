package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i, "name" + i));
                //check for double save
                Assert.assertEquals(storage.size() - 1, i);
            }
        } catch (StorageException e) {
            Assert.fail("StorageException: overflow happened ahead of time");
        }
        storage.save(new Resume("uuid", "Frances Russell"));
    }
}
