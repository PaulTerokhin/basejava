package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final Resume RESUME_1 = new Resume(UUID_1, "John Smith");
    private static final Resume RESUME_2 = new Resume(UUID_2, "Ali Khan");
    private static final Resume RESUME_3 = new Resume(UUID_3, "David Kim");
    private static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST, "Not Exist");

    public AbstractStorageTest(Storage storage) {
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
    }

    @Test
    void update() {
        storage.update(RESUME_1);
        Assertions.assertSame(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_NOT_EXIST));
    }

    @Test
    void getAllSorted() {
        List<Resume> allResumes = storage.getAllSorted();
        List<Resume> expected = new ArrayList<>();
        expected.add(RESUME_2);
        expected.add(RESUME_3);
        expected.add(RESUME_1);
        assertEquals(expected, allResumes);
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_2));
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

        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_2));
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_NOT_EXIST));
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

}