package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    protected boolean isExist(Object searchKey) {
        return (searchKey != null);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String)searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace((String) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.containsKey(uuid)?uuid:null;
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
