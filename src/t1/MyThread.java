package t1;

public class MyThread extends Thread {
    long sleepDur;

    public MyThread(long sleep) {
        this.sleepDur = sleep;
    }

    @Override
    public void run() {
        String tname = getName();
        long id = threadId();
        boolean alive = isAlive();
        System.out.println(tname +
                " | ID: " + id +
                " | Alive: " + alive);
        try {
            sleep(this.sleepDur);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void create(int count) throws InterruptedException {
        MyThread[] threads = new MyThread[count];

        for (int i = 0; i < threads.length; i++) {
            int delay = 300 + i * 200;
            threads[i] = new MyThread(delay);
        }

        for (MyThread t : threads) {
            System.out.println("Vor start(): " + t.getName() +
                    " | ID: " + t.getId() +
                    " | Alive: " + t.isAlive());
            t.start();
        }

        for (MyThread t : threads) {
            t.join();
        }

        for (MyThread t : threads) {
            System.out.println("Nach Beendigung: " + t.getName() +
                    " | ID: " + t.getId() +
                    " | Alive: " + t.isAlive());
        }

        System.out.println("Hauptthread beendet.");
    }
}
