package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME1 = new Resume(UUID_1, "11");
    private static final Resume RESUME2 = new Resume(UUID_2, "22");
    private static final Resume RESUME3 = new Resume(UUID_3, "33");
    private static final Resume RESUME4 = new Resume(UUID_4, "44");
    private static final Resume[] RESUMES = {RESUME1, RESUME2, RESUME3};

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
        assertEquals(3, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(RESUME1, storage.get(RESUME1.getUuid()));
        assertEquals(RESUME2, storage.get(RESUME2.getUuid()));
        assertEquals(RESUME3, storage.get(RESUME3.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(RESUME4);
        assertEquals(4, storage.size());
        assertEquals(RESUME4, storage.get(RESUME4.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME1);
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_1, "NewName");
        storage.update(r);
        assertSame(r, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME4.getUuid());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> aList = Arrays.asList(RESUMES);
        assertEquals(aList,storage.getAllSorted());
        assertEquals(aList.size(),storage.getAllSorted().size());
    }
}