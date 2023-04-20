package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.*;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Integer) searchKey).intValue());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

}
