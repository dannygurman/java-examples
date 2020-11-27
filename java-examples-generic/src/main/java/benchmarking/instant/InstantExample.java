package benchmarking.instant;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class InstantExample {

    //Measure elapsed time in Java 8
    //To get the elapsed execution time in different time units, use methods such as toDays(),
    //toHours(), toMillis(), toMinutes(), toNanos() and getSeconds().

    public static void main(String[] args){
        Instant start = Instant.now();

        //Measure execution time for this method
        methodToTime();

        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        System.out.println("Elapsed "+ timeElapsed + " milis");
    }

    private static void methodToTime() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
