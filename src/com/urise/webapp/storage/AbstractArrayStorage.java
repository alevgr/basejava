package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 1_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Не могу найти резюме " + uuid + " - нет в списке!");
            return null;
        }
        return storage[index];
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Не могу добавить резюме - резюме " + r.getUuid() + " уже существует!");
        } else if (size < storage.length) {

            fillElement(index, r);
            size++;
        } else {
            System.out.println("Не могу добавить резюме - список переполнен!");
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Не могу обновить резюме " + r.getUuid() + " резюме с таким UID не существует!");
            return;
        }
        storage[index] = r;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Не могу найти резюме " + uuid + " - нет в списке!");
        } else {

            deleteElement(index, uuid);
            size--;
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillElement(int index, Resume r);

    protected abstract void deleteElement(int index, String uuid);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}

