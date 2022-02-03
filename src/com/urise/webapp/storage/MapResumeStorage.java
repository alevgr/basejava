package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();
    protected boolean isExist(Object searchKey) {
        return (searchKey != null);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Object getSearchKey(String uuid, String fullName) {
        return storage.get(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> al = new ArrayList<Resume>(storage.values());
        Collections.sort(al);
        return al;
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
