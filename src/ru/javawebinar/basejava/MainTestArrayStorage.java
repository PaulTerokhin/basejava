package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.MapResumeStorage;

public class MainTestArrayStorage {

    private static final Resume RESUME_1 = new Resume("Bob");
    private static final Resume RESUME_2 = new Resume("Anasteisha");
    private static final Resume RESUME_3 = new Resume("Martin");

    public static void main(String[] args) {
//        MapResumeStorage storage = new MapResumeStorage();
//        storage.save(RESUME_1);
//        storage.save(RESUME_3);
//        storage.save(RESUME_2);
//        storage.save(new Resume("Hermann"));


        System.out.println(close10(13,13));



    }

    public static int close10(int a, int b) {
        if (Math.abs(a - 10) > Math.abs(b - 10)) {
            return b;
        } else if (Math.abs(a - 10) < Math.abs(b - 10)) {
            return a;
        } else {
            return 0;
        }






    }
}
