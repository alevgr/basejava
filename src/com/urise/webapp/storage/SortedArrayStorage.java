package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.net.Inet4Address;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void fillResume(int index, Resume r) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        if (index == size-1) {
            storage[index] = null;
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer)index >= 0;
    }
}
