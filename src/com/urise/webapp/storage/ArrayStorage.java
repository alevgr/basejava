package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int sizeResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, sizeResume, null);
        sizeResume = 0;
    }

    public void update(Resume r) {
        if (get(r.getUuid()) == null) {
            System.out.println("Не могу обновить резюме " + r.getUuid() + " - нет в списке!");
            return;
        }
        delete(r.getUuid());
        save(r);
    }

    public void save(Resume r) {
        if (get(r.getUuid()) == null) {
            if (sizeResume < storage.length) {
                storage[sizeResume] = r;
                sizeResume++;
            } else {
                System.out.println("Не могу добавить резюме - список переполнен!");
            }
        } else {
            System.out.println("Не могу добавить резюме - резюме с таким UID уже существует!");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < sizeResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < sizeResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, sizeResume - i - 1);
                sizeResume--;
                return;
            }
        }
        System.out.println("Не могу удалить резюме " + uuid + " - его нет в списке!");
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeResume);
    }

    public int size() {
        return sizeResume;
    }


}

