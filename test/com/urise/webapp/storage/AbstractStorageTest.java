package com.urise.webapp.storage;

import com.urise.webapp.ResumeDataTest;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "null";

    private static final ResumeDataTest RESUME = new ResumeDataTest();

    private static final Resume RESUME_1 = RESUME.getResume(UUID_1, "Name1");
    private static final Resume RESUME_2 = RESUME.getResume(UUID_2, "Name2");
    private static final Resume RESUME_3 = RESUME.getResume(UUID_3, "Name3");
    private static final Resume RESUME_4 = RESUME.getResume(UUID_4, "Name4");

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
        Assert.assertArrayEquals(EMPTY_STORAGE, storage.getAllSorted().toArray());
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_1));
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
        storage.update(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() throws Exception {
        storage.save(RESUME_2);
    }


    protected void assertSize(int expectedNumber) {
        Assert.assertEquals(expectedNumber, storage.size());
    }

    private void assertGet(Resume expectedResume) {
        Assert.assertEquals(expectedResume, storage.get(expectedResume.getUuid()));
    }
}