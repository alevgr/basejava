package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ListStorageTest extends AbstractArrayStorageTest {
    static final int STORAGE_LIMIT = 2_147_483_639;

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    public void saveOverflow() {

    }
}
