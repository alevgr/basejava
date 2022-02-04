package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume((String) uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey,RESUME_COMPARATOR);
    }

    @Override
    protected void fillResume(int index, Resume r) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        if (index == storage.length - 1) {
            storage[index] = null;
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
        }
    }
}
