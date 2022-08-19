package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.util.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static com.urise.webapp.TestData.*;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;

    private static final Resume[] EXPECTED_STORAGE = {R_1, R_2, R_3};
    private static final Resume[] EMPTY_STORAGE = {};

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R_1);
        storage.save(R_2);
        storage.save(R_3);
    }

    @Test
    public void clear() throws Exception {
        assertSize(3);
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(EMPTY_STORAGE, storage.getAllSorted().toArray());
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    public void save() throws Exception {
        assertSize(3);
        storage.save(R_4);
        assertGet(R_4);
        assertSize(4);
    }

    @Test
    public void get() throws Exception {
        for (Resume resume : EXPECTED_STORAGE) {
            assertGet(resume);
        }
    }

    @Test
    public void delete() throws Exception {
        Assert.assertEquals(3, storage.getAllSorted().size());
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.getAllSorted().size());
    }

    @Test
    public void getAllSorted() throws Exception {
        Assert.assertArrayEquals(EXPECTED_STORAGE, storage.getAllSorted().toArray());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(R_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() throws Exception {
        storage.save(R_2);
    }


    protected void assertSize(int expectedNumber) {
        Assert.assertEquals(expectedNumber, storage.size());
    }

    private void assertGet(Resume expectedResume) {
        Assert.assertEquals(expectedResume, storage.get(expectedResume.getUuid()));
    }
}