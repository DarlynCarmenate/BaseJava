import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int numResumes;

    void clear() {
        Arrays.fill(storage, 0, numResumes, null);
        numResumes = 0;
    }

    void save(Resume r) {
        storage[numResumes] = r;
        numResumes++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < numResumes; i++) {
            if (uuid.equals(storage[i].toString())) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < numResumes; i++) {
            if (uuid.equals(storage[i].toString())) {
                numResumes--;
                System.arraycopy(storage, i + 1, storage, i, numResumes - i);
                storage[numResumes] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, numResumes);
    }

    int size() {
        return numResumes;
    }
}
