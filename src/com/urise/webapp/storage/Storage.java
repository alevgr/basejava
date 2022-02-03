package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

public interface Storage {

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void update(Resume r);

    void save(Resume r);

    void delete(String uuid);

    void clear();

    int size();
}

