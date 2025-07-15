package t23;


import java.util.ArrayList;

public class Buffer {
    private ArrayList<Character> buffer;
    private int size;
    private int used;

    public Buffer(int size) throws InterruptedException {
        used = 0;
        this.size = size;
        buffer = new ArrayList<>(size);
    }

    public synchronized void put(Character c) throws InterruptedException {
        while (used >= size) {
            wait();
        }
        used++;
        buffer.add(c);
    }

    public synchronized Character take() throws InterruptedException {
        var value = buffer.removeFirst();
        used--;
        notifyAll();
        return value;
    }
}