package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(Resume resume, int index) {
        int newResumeIndex = -index - 1;
        System.arraycopy(storage, newResumeIndex, storage, newResumeIndex + 1, size - newResumeIndex);
        storage[newResumeIndex] = resume;
    }

    @Override
    protected void shiftElements(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
