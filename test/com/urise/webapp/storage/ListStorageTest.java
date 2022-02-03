package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    // только теперь во вкладке run над всем тестом "ListStorageTest" висит метка "Test ignored." - это так и должно быть?
    public void saveOverflow() {
    }
}
