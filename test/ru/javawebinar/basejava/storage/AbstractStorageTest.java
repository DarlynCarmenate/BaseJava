package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.*;
import org.junit.runners.Suite;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    private static final String UUID_NOT_FOUND = "dummy";

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

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
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
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
        assertThrows(ExistStorageException.class, () -> storage.save(testResume));
    }

    @Test
    void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(newResume.getUuid()));
    }

    @Test
    void updateNotExist() throws Exception {
        Resume notExist = new Resume();
        assertThrows(NotExistStorageException.class, () -> storage.update(notExist));
    }

    @Test
    void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () ->
                storage.delete(UUID_NOT_FOUND));
    }

    @Test
    void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () ->
                storage.get(UUID_NOT_FOUND));
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        assertSize(0);
        assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    void getAll() throws Exception {
        Resume[] testStorage = new Resume[]{RESUME_1, RESUME_2, RESUME_3};;
        assertSize(3);
        assertArrayEquals(testStorage, storage.getAll());
    }

    @Test
    void size() throws Exception {
        assertSize(3);
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }
    private void assertGet(Resume r) {
        String rUuid = r.getUuid();
        assertEquals(rUuid, storage.get(rUuid).getUuid());
    }
}