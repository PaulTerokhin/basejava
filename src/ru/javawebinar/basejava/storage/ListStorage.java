package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import java.util.*;

public class ListStorage implements Storage {

    private final List<Resume> storageList = new ArrayList<>();

    public int size() {
        return storageList.size();
    }

    public void clear() {
        storageList.clear();
    }

    public void update(Resume resume) {
        int index = storageList.indexOf(resume);
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storageList.set(index, resume);
        }
    }

    public Resume[] getAll() {
        return storageList.toArray(new Resume[0]);
    }

    public void save(Resume resume) {
        int index = storageList.indexOf(resume);
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            storageList.add(resume);
        }
    }

    public void delete(String uuid) {
        int index = storageList.indexOf(new Resume(uuid));
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            storageList.remove(index);
        }
    }

    public Resume get(String uuid) {
        int index = storageList.indexOf(new Resume(uuid));
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storageList.get(index);
    }

}
