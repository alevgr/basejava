package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_1, storage.get(UUID_1).toString());
        Assert.assertEquals(UUID_2, storage.get(UUID_2).toString());
        Assert.assertEquals(UUID_3, storage.get(UUID_3).toString());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        Resume r = new Resume("uuid4");
        Assert.assertEquals(3, storage.size());
        storage.save(r);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        Resume r;
        try {
            r = new Resume("uuid4");
            storage.save(r);
        } catch (StorageException e) {
            Assert.fail("Overflow was earlier than expected");
        }

        r = new Resume("uuid5");
        storage.save(r);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        Resume r = new Resume(UUID_1);
        storage.save(r);
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_1);
        storage.update(r);
        Assert.assertEquals(r, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume r = new Resume("dummy");
        storage.update(r);
    }

    @Test
    public void delete() {
        Assert.assertEquals(3, storage.size());
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.delete(UUID_2);
        Assert.assertEquals(1, storage.size());
        storage.delete(UUID_3);
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(storage.size(), storage.getAll().length);
    }
}