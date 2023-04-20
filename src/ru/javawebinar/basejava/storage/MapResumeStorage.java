package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected void doSave(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void doDelete(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }


}
