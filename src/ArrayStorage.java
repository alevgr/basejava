import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length && storage[i] != null; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length && storage[i] != null; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j < storage.length && storage[j] != null; j++) {
                    storage[j] = storage[j + 1];
                }
            }
        }
    }

    Resume[] getAll() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return Arrays.copyOf(storage, i);
            }
        }
        return new Resume[0];
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return 0;
    }
}

