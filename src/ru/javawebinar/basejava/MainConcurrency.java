package ru.javawebinar.basejava;

public class MainConcurrency {
    public static final Object Lock1 = new Object();
    public static final Object Lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }

    private static void performSynchronizedBlock(Object lock1, Object lock2, String message1, String message2) {
        synchronized (lock1) {
            System.out.println(message1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(message2);
            synchronized (lock2) {
                System.out.println("Thread: Holding lock 1 & 2...");
            }
        }
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            performSynchronizedBlock(Lock1, Lock2, "Thread 1: Holding lock 1...", "Thread 1: Waiting for lock 2...");
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            performSynchronizedBlock(Lock2, Lock1, "Thread 2: Holding lock 2...", "Thread 2: Waiting for lock 1...");
        }
    }
}

