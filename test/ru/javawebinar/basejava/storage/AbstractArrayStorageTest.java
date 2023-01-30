package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.*;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.fail;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

class AbstractArrayStorageTest {
    private final Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    protected static final Resume RESUME_1 = new Resume(UUID_1);
    protected static final Resume RESUME_2 = new Resume(UUID_2);
    protected static final Resume RESUME_3 = new Resume(UUID_3);
    protected static final Resume RESUME_4 = new Resume(UUID_4);
    private static final Resume UUID_NOT_FOUND = new Resume ("dummy");

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void save() throws Exception {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void storageOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                Resume r = new Resume();
                storage.save(r);
            }
        } catch(StorageException e) {
            fail("Something goes wrong", e.getCause());
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(RESUME_4));
    }

    @Test
    void saveAlreadyExist() throws Exception {
        Resume testResume = new Resume("uuid1");
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(testResume));
    }

    @Test
    void update() throws Exception {
        storage.update(RESUME_1);
        Assertions.assertSame(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test
    void updateNotExist() throws Exception {
        Resume notExist = new Resume();
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(notExist));
    }

    @Test
    void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.delete(UUID_NOT_FOUND.getUuid()));
    }

    @Test
    void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    void assertGet(Resume r) {
        String rUuid = r.getUuid();
        Assertions.assertEquals(rUuid, storage.get(rUuid).getUuid());
    }

    @Test
    void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get(UUID_NOT_FOUND.getUuid()));
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        assertSize(0);
        Assertions.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    void getAll() throws Exception {
        Resume[] testStorage = new Resume[3];
        testStorage[0] = RESUME_1;
        testStorage[1] = RESUME_2;
        testStorage[2] = RESUME_3;
        assertSize(3);
    }

    void assertSize(int expectedSize) {
        Assertions.assertEquals(expectedSize, storage.size());
    }
    @Test
    void size() throws Exception {
        assertSize(3);
    }
}