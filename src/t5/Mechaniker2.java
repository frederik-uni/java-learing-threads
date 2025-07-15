package t5;


public class Mechaniker2 extends Thread {
    private volatile boolean leftAvailable;
    private Mechaniker2 rightPerson;

    public void setRightPerson(Mechaniker2 rightPerson) {
        this.rightPerson = rightPerson;
    }

    public synchronized void takeLeft() throws RuntimeException {
        if (!leftAvailable) {
            throw new RuntimeException("e");
        }
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

    public void takeRight() throws RuntimeException {
        rightPerson.takeLeft();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.takeLeft();
                this.takeRight();
                try {
                    sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.putLeft();
                this.putRight();
                break;
            } catch (Exception _) {
            }
        }

    }

    public static void create(int len) throws InterruptedException {
        Mechaniker2[] threads = new Mechaniker2[len];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Mechaniker2();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].setRightPerson(threads[i + 1 % threads.length]);
        }
        for (Mechaniker2 thread : threads) {
            thread.start();
        }

        for (Mechaniker2 thread : threads) {
            thread.join();
        }
    }
}
