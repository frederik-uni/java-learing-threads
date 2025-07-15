package t20;


import java.util.concurrent.Semaphore;

public class Mechaniker extends Thread {
    private final Semaphore leftAvailable = new Semaphore(1);
    private final int id;
    private Mechaniker rightPerson;

    public void setRightPerson(Mechaniker rightPerson) {
        this.rightPerson = rightPerson;
    }

    public void takeLeft() throws InterruptedException {
        leftAvailable.acquire();
    }

    public void putLeft() {
        if (leftAvailable.availablePermits() == 1) {
            throw new RuntimeException("cant put if its already there");
        }
        leftAvailable.release();
    }

    public void putRight() {
        rightPerson.putLeft();
    }

    public void takeRight() throws InterruptedException {
        rightPerson.takeLeft();
    }
    public boolean leftFirst() {
        return id % 2 == 0;
    }

    @Override
    public void run() {
        try {
            if (leftFirst()) {
                this.takeLeft();
                this.takeRight();
            }else {
                this.takeRight();
                this.takeLeft();
            }
            sleep((long) (Math.random() * 1000));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.putLeft();
        this.putRight();
    }

    public Mechaniker(int id) {
        this.id = id;
    }

    public static void create(int len) throws InterruptedException {
        Mechaniker[] threads = new Mechaniker[len];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Mechaniker(i);
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

