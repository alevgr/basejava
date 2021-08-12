import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int sizeResume = 0;

    void clear() {
        Arrays.fill(storage, 0, sizeResume, null);
        sizeResume = 0;
    }

    void save(Resume r) {
        if (sizeResume < storage.length) {
            storage[sizeResume] = r;
            sizeResume++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < sizeResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < sizeResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, sizeResume - i);
                sizeResume--;
            }
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, sizeResume);
    }

    int size() {
        return sizeResume;
    }
}

