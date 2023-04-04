package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }
    private static final Resume resume1 = new Resume("uuid1");
    private static final Resume resume2 = new Resume("uuid2");
    private static final Resume resume3 = new Resume("uuid3");

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void update() {
        storage.update(resume1);
        Resume updatedResume = storage.get("uuid1");
        Assertions.assertEquals(resume1, updatedResume);

        Resume resume6 = new Resume("uuid6");
        try {
            storage.update(resume6);
            Assertions.fail("Expected NotExistStorageException was not thrown");
        } catch (NotExistStorageException e) {
            // expected exception
        }
    }

    @Test
    void getAll() {
    Resume[] result = storage.getAll();
    Assertions.assertEquals(3, result.length);
    Assertions.assertEquals(resume1, result[0]);
    Assertions.assertEquals(resume2, result[1]);
    Assertions.assertEquals(resume3, result[2]);
    }

    @Test
    void saveShouldThrowExistStorageException() {
        try {
            storage.save(new Resume("uuid1"));
            Assertions.fail("Expected ExistStorageException was not thrown");
        } catch (ExistStorageException e) {
            // expected exception
        }
    }

    @Test
    void saveShouldThrowStorageException() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assertions.fail("Unexpected StorageException during saving");
        }

        try {
            storage.save(new Resume("uuid" + (AbstractArrayStorage.STORAGE_LIMIT + 1)));
            Assertions.fail("Expected StorageException was not thrown");
        } catch (StorageException e) {
            Assertions.assertEquals("Storage overflow", e.getMessage());
        }
    }

    @Test
    void save() {
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    void delete() {
        try {
            storage.delete("uuid2");
        } catch (NotExistStorageException e) {
            Assertions.fail("Unexpected NotExistStorageException during deleting existing resume");
        }

        try {
            storage.delete("uuid2");
            Assertions.fail("Expected NotExistStorageException was not thrown");
        } catch (NotExistStorageException e) {
            Assertions.assertEquals(2, storage.size());
        }
    }

    @Test
    void get() {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        Resume result = storage.get("uuid4");
        Assertions.assertEquals(resume, result);

        try {
            storage.get("uuid75");
            Assertions.fail("Expected NotExistStorageException was not thrown");
        } catch (NotExistStorageException e) {
            // expected exception
        }
    }

    @Test
    public void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));;
    }
}