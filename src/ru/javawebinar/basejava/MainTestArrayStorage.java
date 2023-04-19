package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.MapFullnameStorage;

public class MainTestArrayStorage {
    private static final String UUID_1 = "buid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "zuid3";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final Resume RESUME_1 = new Resume(UUID_1, "Bob");
    private static final Resume RESUME_2 = new Resume(UUID_2, "Anasteisha");
    private static final Resume RESUME_3 = new Resume(UUID_3, "Martin");
    private static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);

    public static void main(String[] args) {
        MapFullnameStorage storage = new MapFullnameStorage();
        storage.save(RESUME_1);
        storage.save(RESUME_3);
        storage.save(RESUME_2);
        storage.save(new Resume("Hermann"));

        System.out.println(storage.getAllSorted());

    }
}
