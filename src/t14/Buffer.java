package t14;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Buffer {
    private final ArrayList<Character> buffer;
    private final Semaphore used;
    private final Semaphore remaining;
    public Buffer(int size) throws InterruptedException {
        used = new Semaphore(0);
        remaining = new Semaphore(size);
        buffer = new ArrayList<>(size);
    }

    public synchronized void put(Character c) throws InterruptedException {
        remaining.acquire();
        buffer.add(c);
        used.release();
    }
    public synchronized Character take() throws InterruptedException {
        used.acquire();
        var value = buffer.removeFirst();
        remaining.release();
        return value;
    }
}
