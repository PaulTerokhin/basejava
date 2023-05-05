package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

public class MainTestArrayStorage {

    public static void main(String[] args) {

        System.out.println(doubleX("xaxxx"));

    }
    public static boolean doubleX(String str) {
        char[] array = str.toCharArray();
        for(int i = 0; i < str.length(); i++) {
            if (array[i] == 'x') {
                if((i + 1) <= str.length()) {
                    if (array[i + 1] == 'x') {
                        return true;
                    }
                }
                break;
            }
        }
        return false;






    }
}
