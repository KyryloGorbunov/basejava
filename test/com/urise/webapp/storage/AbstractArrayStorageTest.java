package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {
    private final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume r1 = new Resume(UUID_1);
    private static final Resume r2 = new Resume(UUID_2);
    private static final Resume r3 = new Resume(UUID_3);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() throws Exception {
        Assert.assertEquals(3, storage.getAll().length);
        storage.clear();
        Assert.assertEquals(0, storage.getAll().length);
    }

    @Test
    public void update() throws Exception {
        Assert.assertEquals(3, storage.getAll().length);
        storage.update(storage.get("uuid3"));
        Assert.assertEquals(r3, storage.get("uuid3"));
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void save() throws Exception {
        Assert.assertEquals(3, storage.getAll().length);
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        Assert.assertEquals(r4, storage.get("uuid4"));
        Assert.assertEquals(4, storage.getAll().length);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(r1, storage.get("uuid1"));
        Assert.assertEquals(r2, storage.get("uuid2"));
        Assert.assertEquals(r3, storage.get("uuid3"));
    }

    @Test
    public void delete() throws Exception {
        Assert.assertEquals(3, storage.getAll().length);
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.getAll().length);
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(3, storage.getAll().length);
        Assert.assertEquals(r1, storage.get("uuid1"));
        Assert.assertEquals(r2, storage.get("uuid2"));
        Assert.assertEquals(r3, storage.get("uuid3"));
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        Assert.assertEquals(r4, storage.get("uuid4"));
        Assert.assertEquals(4, storage.getAll().length);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
        storage.delete("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() throws Exception {
        storage.save(r2);
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverFlow() throws Exception {
        storage.clear();
        for (int i = 0; i < 10000; i++) {
            storage.save(new Resume());
        }
        storage.save(new Resume());
        Assert.fail("StorageException: Storage overflow");
    }
}