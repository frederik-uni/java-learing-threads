package t5;

import t1.MyThread;

public class Mechaniker extends Thread {
    private volatile boolean leftAvailable;
    private Mechaniker rightPerson;

    public void setRightPerson(Mechaniker rightPerson) {
        this.rightPerson = rightPerson;
    }

    public synchronized void takeLeft() {
        while (!leftAvailable) onSpinWait();
        leftAvailable = false;
    }

    public synchronized void putLeft() {
        if (leftAvailable) {
            throw new RuntimeException("cant put if its already there");
        }
        leftAvailable = true;
    }

    public void putRight() {
        rightPerson.putLeft();
    }

    public void takeRight() {
        rightPerson.takeLeft();
    }

    @Override
    public void run() {
        this.takeLeft();
        this.takeRight();
        try {
            sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.putLeft();
        this.putRight();
    }

    public static void create(int len) throws InterruptedException {
        Mechaniker[] threads = new Mechaniker[len];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Mechaniker();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].setRightPerson(threads[i + 1 % threads.length]);
        }
        for (Mechaniker thread : threads) {
            thread.start();
        }

        for (Mechaniker thread : threads) {
            thread.join();
        }
    }
}
