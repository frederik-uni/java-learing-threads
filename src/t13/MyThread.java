package t13;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread{
    private int counter;
    private Semaphore lock;

    public MyThread() {
        this.counter = 0;
        this.lock = new Semaphore(1);
    }

    @Override
    public void run() {
        try {
            this.sum_up(2_000_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sum_up(int N) throws InterruptedException {
        for (int i = 0; i < N; i++) {
            this.increment();
        }
    }

    public void increment() throws InterruptedException {
        lock.acquire();
        counter++;
        lock.release();
    }

    public static void create(boolean mainFrist, long sleeping) throws InterruptedException {
        var t = new MyThread();
        if (mainFrist) {
            t.sum_up(2_000_000);
            sleep(sleeping);
            t.start();
            t.join();
        }else {
            t.start();
            sleep(sleeping);
            t.sum_up(2_000_000);
            t.join();
        }
        System.out.println(t.counter);
    }
}
