package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "null";

    private static final Resume RESUME_1 = new Resume(UUID_1, "Christopher Bush");
    private static final Resume RESUME_2 = new Resume(UUID_2, "David Little");
    private static final Resume RESUME_3 = new Resume(UUID_3, "James Buchanan");
    private static final Resume RESUME_4 = new Resume(UUID_4, "Frances Russell");

    private static final Resume[] EXPECTED_STORAGE = {RESUME_1, RESUME_2, RESUME_3};
    private static final Resume[] EMPTY_STORAGE = {};

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }



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
        storage.update(storage.get(UUID_3));
        Assert.assertSame(RESUME_3, storage.get(UUID_3));
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
        for (Resume resume : EXPECTED_STORAGE) {
            assertGet(resume);
        }
    }

    @Test
    public void delete() throws Exception {
        Assert.assertEquals(3, storage.getAll().length);
        storage.delete(UUID_1);
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
        storage.update(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() throws Exception {
        storage.save(RESUME_2);
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

    protected void assertSize(int expectedNumber) {
        Assert.assertEquals(expectedNumber, storage.size());
    }

    private void assertGet(Resume expectedResume) {
        Assert.assertEquals(expectedResume, storage.get(expectedResume.toString()));
    }
}