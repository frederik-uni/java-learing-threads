package t18;

import java.util.concurrent.Semaphore;

public class Barrier {
    private int counter = 0;
    private int len;
    private Semaphore semaphore = new Semaphore(0);

    public Barrier(int len) {
        this.len = len;
    }

    public synchronized void waitForBarrier() throws InterruptedException {
        counter += 1;
        if (counter == len) {
            semaphore.release(len - 1);
        }else {
            semaphore.acquire();
        }
    }
}
