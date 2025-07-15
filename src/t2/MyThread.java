package t2;

public class MyThread extends Thread{
    private int counter;

    public MyThread() {
        this.counter = 0;
    }

    @Override
    public void run() {
       this.sum_up(2_000_000);
    }

    public void sum_up(int N) {
        for (int i = 0; i < N; i++) {
            this.increment();
        }
    }

    public void increment() {
        counter++;
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
