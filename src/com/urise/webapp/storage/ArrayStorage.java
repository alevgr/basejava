package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10_000];
    private int countResume = 0;

    private int getIndexByUid(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void update(Resume r) {
        int index = getIndexByUid(r.getUuid());
        if (index < 0) {
            System.out.println("Не могу обновить резюме " + r.getUuid() + " резюме с таким UID не существует!");
            return;
        }
        Resume buf = new Resume();
        buf.setUuid(r.getUuid());
        this.storage[index] = buf;
    }

    public void save(Resume r) {
        int index = getIndexByUid(r.getUuid());
        if (index != -1) {
            System.out.println("Не могу добавить резюме - резюме с таким UID уже существует!");
        } else {
            if (countResume < storage.length) {
                storage[countResume] = r;
                countResume++;
            } else {
                System.out.println("Не могу добавить резюме - список переполнен!");
            }
        }
    }

    public Resume get(String uuid) {
        int index = getIndexByUid(uuid);
        if (index < 0) {
            System.out.println("Не могу найти резюме " + uuid + " - нет в списке!");
            return null;
        } else {
            return this.storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndexByUid(uuid);
        if (index < 0) {
            System.out.println("Не могу найти резюме " + uuid + " - нет в списке!");
        } else {
            System.arraycopy(storage, index + 1, storage, index, countResume - index - 1);
            countResume--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }
}

