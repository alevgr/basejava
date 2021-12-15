package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (size < storage.length) {
            fillResume((int) searchKey, r);
            size++;
        } else {
            throw new StorageException("Storage owerflow!", r.getUuid());
        }
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(int) searchKey] = r;
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
        size--;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillResume(int index, Resume r);

    protected abstract void deleteResume(int index);

    protected abstract Integer getSearchKey(String uuid);

    protected abstract boolean isExist(Object index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}

