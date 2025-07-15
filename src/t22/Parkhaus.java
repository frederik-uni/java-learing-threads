package t22;

public class Parkhaus {
    private int capacity;
    private int count;

    public Parkhaus(int capacity) {
        this.capacity = capacity;
        this.count = 0;
    }

    public synchronized void in() throws InterruptedException {
        while (capacity <= count) {
            wait();
        }
        count++;
    }

    public synchronized void out() throws InterruptedException {
        count--;
        notifyAll();
    }

    static class Car extends Thread {
        private final Parkhaus p;

        public Car(Parkhaus p) {
            this.p = p;
        }

        public void run() {
            try {
                sleep((int) (Math.random() * 1000));
                p.in();
                sleep((int) (Math.random() * 1000));
                p.out();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
