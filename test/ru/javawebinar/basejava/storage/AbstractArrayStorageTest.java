package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        Assertions.assertArrayEquals(new Storage[0], storage.getAll());
    }

    @Test
    void update() {
        storage.update(RESUME_1);
        Assertions.assertSame(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        try {
            storage.update(RESUME_NOT_EXIST);
            Assertions.fail("Expected NotExistStorageException was not thrown");
        } catch (NotExistStorageException e) {
            // expected exception
        }
    }

    @Test
    void getAll() {
        Resume[] actual = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Assertions.assertArrayEquals(actual, storage.getAll());
    }

    @Test
    void saveExist() {
        try {
            storage.save(RESUME_2);
            Assertions.fail("Expected ExistStorageException was not thrown");
        } catch (ExistStorageException e) {
            // expected exception
        }
    }

    @Test
    void saveOverflow() {
        try {
            storage.clear();
            for (int i = 1; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assertions.fail("Unexpected StorageException during saving");
        }

        try {
            storage.save(new Resume());
            Assertions.fail("Expected StorageException was not thrown");
        } catch (StorageException e) {
            Assertions.assertEquals("Storage overflow", e.getMessage());
        }
    }

    @Test
    void save() {
        storage.save(RESUME_NOT_EXIST);
        assertGet(RESUME_NOT_EXIST);
        assertSize(4);
    }

    @Test
    void delete() {
        try {
            storage.delete(UUID_2);
            assertSize(2);
        } catch (NotExistStorageException e) {
            Assertions.fail("Unexpected NotExistStorageException during deleting existing resume");
        }

        try {
            storage.get(UUID_2);
            Assertions.fail("Expected NotExistStorageException was not thrown");
        } catch (NotExistStorageException e) {
            // expected exception
        }
    }

    @Test
    void deleteNotExist() {
        try {
            storage.delete(UUID_NOT_EXIST);
            Assertions.fail("Expected NotExistStorageException was not thrown");
        } catch (NotExistStorageException e) {
            Assertions.assertEquals(3, storage.size());
        }

    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getNotExist() {
        try {
            storage.get(UUID_NOT_EXIST);
            Assertions.fail("Expected NotExistStorageException was not thrown");
        } catch (NotExistStorageException e) {
            // expected exception
        }
    }

    void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }
}