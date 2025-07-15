package t11;

import java.util.concurrent.Semaphore;

public class Parkhaus {
    private Semaphore capacity;

    public Parkhaus(int capacity) {
        this.capacity = new Semaphore(capacity);
    }

    public void in() throws InterruptedException {
        capacity.acquire();
    }

    public void out() throws InterruptedException {
        capacity.release();
    }

    static class Car extends Thread {
        private final Parkhaus p;
        public Car(Parkhaus p) {
            this.p = p;
        }
        public void run() {
            try {
                sleep((int)(Math.random() * 1000));
                p.in();
                sleep((int)(Math.random() * 1000));
                p.out();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
