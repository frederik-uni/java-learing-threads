package t25;

import java.util.ArrayList;

public class Parkhaus {
    private int capacity;
    private int count;
    private ArrayList<Car> carsWaiting;

    public Parkhaus(int capacity) {
        this.capacity = capacity;
        this.count = 0;
    }

    public synchronized void in(Car car) throws InterruptedException {
        carsWaiting.add(car);
        while (capacity <= count || carsWaiting.getFirst() != car) {
            wait();
        }
        if (carsWaiting.getFirst() == car) {
            carsWaiting.removeFirst();
        }
        count++;
    }

    public synchronized void out() throws InterruptedException {
        count--;
        notifyAll();
    }

    public static class Car extends Thread {
        private final Parkhaus p;

        public Car(Parkhaus p) {
            this.p = p;
        }

        public void run() {
            try {
                sleep((int) (Math.random() * 1000));
                p.in(this);
                sleep((int) (Math.random() * 1000));
                p.out();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
