package ru.javawebinar.basejava;

public class MainConcurrency {
    public static final Object Lock1 = new Object();
    public static  final Object Lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (Lock1) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }


}
