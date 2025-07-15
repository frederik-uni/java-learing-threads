package t8;

import java.time.Duration;
import java.time.Instant;

public class MyTimer extends Thread {
    private long sleepTime;

    public MyTimer(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void run() {
        Instant start = Instant.now();
        long wait = sleepTime;
        while (wait > 0) {
            try {
                sleep(wait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            long millis = duration.toMillis();
            if (millis >= wait) {
                wait = 0;
            }else {
                wait -= millis;
            }
        }
    }
}
