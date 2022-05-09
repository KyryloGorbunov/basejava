import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < storage.length) {
            if (size == 0) {
                storage[size] = r;
                size++;
            } else {
                for (int i = 0; i < size; i++) {
                    boolean isEqual = r.uuid.equals(storage[i].uuid);
                    if (!isEqual && i == size - 1) {
                        storage[size] = r;
                        size++;
                    } else if (isEqual) {
                        break;
                    }
                }
            }
        }
    }

    public Resume get(String uuid) {
        Resume r = null;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                r = storage[i];
                break;
            }
        }
        return r;
    }

    public void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = Arrays.copyOf(storage, size);
        return resumes;
    }

    public int size() {
        return size;
    }
}
