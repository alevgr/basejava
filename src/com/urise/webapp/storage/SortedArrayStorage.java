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
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {
        int j = Arrays.binarySearch(storage, 0, size, r);
        if (j >= 0) {
            System.out.println("Такое значение уже есть в Storage!");
            return;
        }
        j = -j - 1;
        System.arraycopy(storage, j, storage, j + 1, size - j);
        storage[j] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void clear() {

    }
}
