import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10_000];

    void clear() {
        Arrays.fill(storage, 0, size() - 1, null);
    }

    void save(Resume resume) {
        int index = size();
        if (index < storage.length) {
            storage[index] = resume;
        } else {
            System.out.println("The storage is full");
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int deletedIndex = -1;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                deletedIndex = i;
                break;
            }
        }

        if (deletedIndex == -1) {
            System.out.println("Resume with uuid " + uuid + " not found");
            return;
        }

        for (int i = deletedIndex; i < size() - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size() - 1] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            }
            count++;
        }
        return count;
    }
}
