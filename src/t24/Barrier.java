package t24;

import java.util.concurrent.Semaphore;

public class Barrier {
    private int counter = 0;
    private final int len;

    public Barrier(int len) {
        this.len = len;
    }

    public synchronized void waitForBarrier() throws InterruptedException {
        counter += 1;
        if (counter == len) {
            notifyAll();
        }
        while(counter < len) {
            wait();
        }

    }
}

