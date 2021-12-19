package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractArrayStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Ignore
    @Test
    // только теперь во вкладке run над всем тестом "ListStorageTest" висит метка "Test ignored." - это так и должно быть?
    public void saveOverflow() {
    }
}
