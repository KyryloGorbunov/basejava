package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final int STORAGE_LIMIT = 10000;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "null";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    private static final Resume[] EXPECTED_STORAGE = {RESUME_1, RESUME_2, RESUME_3};
    private static final Resume[] EMPTY_STORAGE = {};

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() throws Exception {
        assertSize(3);
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(EMPTY_STORAGE, storage.getAll());
    }

    @Test
    public void update() throws Exception {
        assertSize(3);
        storage.update(storage.get("uuid3"));
        Assert.assertSame(RESUME_3, storage.get("uuid3"));
        assertSize(3);
    }

    @Test
    public void save() throws Exception {
        assertSize(3);
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void get() throws Exception {
        assertGetAllResumes();
    }

    @Test
    public void delete() throws Exception {
        Assert.assertEquals(3, storage.getAll().length);
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.getAll().length);
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertArrayEquals(EXPECTED_STORAGE, storage.getAll());
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
        storage.update(storage.get(UUID_NOT_EXIST));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() throws Exception {
        storage.save(RESUME_2);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            if (storage.getAll().length >= STORAGE_LIMIT) {
                Assert.fail("StorageException: overflow happened ahead of time");
            } else {
                storage.save(new Resume());
            }
        }
        storage.save(new Resume());
        Assert.fail("StorageException: Storage overflow");
    }

    private void assertSize(int expectedNumber) {
        Assert.assertEquals(expectedNumber, storage.size());
    }

    private void assertGet(Resume expectedResume) {
        Assert.assertEquals(expectedResume, storage.get(expectedResume.toString()));
    }

    private void assertGetAllResumes() {
        for (int i = 0; i < EXPECTED_STORAGE.length; i++) {
            assertGet(EXPECTED_STORAGE[i]);
        }
    }
}