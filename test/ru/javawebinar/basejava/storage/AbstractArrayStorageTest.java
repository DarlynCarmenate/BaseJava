package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.fail;

class AbstractArrayStorageTest {

    private Storage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest() {
        this.storage.save(new Resume(UUID_1));
        this.storage.save(new Resume(UUID_2));
        this.storage.save(new Resume(UUID_3));
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void save() throws Exception {
        Resume newResume = new Resume("new");
        storage.save(newResume);
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    void storageOverflow() throws Exception {
        Resume[] r = new Resume[3];
        try {
            r[0] = new Resume("uuid1");
            r[1] = new Resume("uuid2");
            r[2] = new Resume("uuid3");
        } catch(Exception e) {
            fail("Something goes wrong", e.getCause());
        }
        Resume overResume = new Resume("uuid4");
        Assertions.assertThrows(StorageException.class, () -> {
            r[3] = overResume;
        });
    }

    @Test
    void saveAlreadyExist() throws Exception {
        Resume testResume = new Resume("uuid1");
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(testResume);
        });
    }

    @Test
    void update() throws Exception {
        Resume test = storage.get("uuid1");
        int num = test.hashCode();
        Resume newResume = new Resume("uuid1");
        storage.update(newResume);
        int num2 = newResume.hashCode();
        Assertions.assertNotEquals(num, num2);
    }

    @Test
    void updateNotExist() throws Exception {
        Resume notExist = new Resume();
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(notExist);
        });
    }

    @Test
    void delete() throws Exception {
        storage.delete("uuid1");
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
    }

    @Test
    void get() throws Exception {
        Resume testResume = new Resume("uuid1");
        Assertions.assertEquals(testResume, storage.get("uuid1"));
    }

    @Test
    void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void getAll() throws Exception {
        Resume[] testStorage = new Resume[3];
        testStorage[0] = new Resume("uuid1");
        testStorage[1] = new Resume("uuid2");
        testStorage[2] = new Resume("uuid3");
        Assertions.assertArrayEquals(testStorage, storage.getAll());
    }

    @Test
    void size() throws Exception {
        Assertions.assertEquals(3, storage.size());
    }
}