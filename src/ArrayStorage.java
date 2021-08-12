import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    Integer SizeResume = 0;

    void clear() {
        Arrays.fill(storage, 0, SizeResume, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                SizeResume ++;
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
                SizeResume --;
            }
        }
    }

    Resume[] getAll() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                SizeResume = i;
                return Arrays.copyOf(storage, i);
            }
        }
        return new Resume[0];
    }

    int size() {
        return SizeResume;
    }
}

