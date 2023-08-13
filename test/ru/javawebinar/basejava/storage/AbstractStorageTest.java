package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.storage.ResumeTestData.getResume;


public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\my_java\\Basejava\\basejava\\lesson");
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = getResume(UUID_1, "Name_1");
    private static final Resume RESUME_2 = getResume(UUID_2, "Name_2");
    private static final Resume RESUME_3 = getResume(UUID_3, "Name_3");
    private static final Resume RESUME_4 = getResume(UUID_4, "Name_4");
    private static final String UUID_NOT_FOUND = "dummy";
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
    void saveAlreadyExist() throws Exception {
        Resume testResume = new Resume("uuid1", "Name1");
        assertThrows(ExistStorageException.class, () -> storage.save(testResume));
    }

    @Test
    void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "Name1");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test
    void updateNotExist() throws Exception {
        Resume notExist = new Resume("name");
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
        List<Resume> list = new ArrayList<>();
        assertIterableEquals(list, storage.getAllSorted());
    }

    @Test
    void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
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