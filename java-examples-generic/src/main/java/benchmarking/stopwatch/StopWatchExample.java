package benchmarking.stopwatch;

import org.apache.commons.lang3.time.StopWatch;

public class StopWatchExample {

    public static void main(String[] args) {
        StopWatchExample stopWatchExample = new StopWatchExample();
        stopWatchExample.measureTime();
    }

    private void measureTime() {
        // Create an instance
        StopWatch stopWatch = new StopWatch();

        // Start the watch, do some task and stop
        stopWatch.start();
        doSomething(5000);
        stopWatch.stop();

        // Print out the total time of the watch
        System.out.println("Time: " + stopWatch.getTime());
    }

    private void doSomething(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
