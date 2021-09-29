package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {

    Resume get(String uuid);

    Resume[] getAll();

    void update(Resume r);

    void save(Resume r);

    void delete(String uuid);

    void clear();

    int size();
}

