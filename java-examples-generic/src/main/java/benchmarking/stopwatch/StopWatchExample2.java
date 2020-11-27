package benchmarking.stopwatch;

//Beside doing a simple timing calculation using the start() and stop()
////followed by the getTime() methods,
//the StopWatch class also provides methods
//for splitting the time, suspend and resuming the stopwatch.
//You can use the split(), suspend() and resume() method respectively.

import org.apache.commons.lang3.time.StopWatch;

public class StopWatchExample2 {

    public static void main(String[] args) {
        StopWatchExample2 stopWatchExample2 = new StopWatchExample2();
        stopWatchExample2.timing();
      /*  Result:
        Split 1: 00:00:02.999
        Split 2: 00:00:05.548  // 3 + 2.5
        Split 3: 00:00:06.549 // 3 + 2.5 + 1
        Time: 6551*/
    }

    private void timing() {
        // Create an instance of StopWatch and start the stopwatch.
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // Do some task and split the stopwatch time.
        doSomeTask(3000);
        stopWatch.split();
        System.out.println("Split 1: " + stopWatch.toSplitString());

        // Suspend the stopwatch and resume the stopwatch.
        stopWatch.suspend();
        doSomeTask(4000);
        stopWatch.resume();

        // Do some task and split the stopwatch time.
        doSomeTask(2500);
        stopWatch.split();
        System.out.println("Split 2: " + stopWatch.toSplitString());

        // Do some task and split the stopwatch time.
        doSomeTask(1000);
        stopWatch.split();
        System.out.println("Split 3: " + stopWatch.toSplitString());

        // Stop the stopwatch and the the total execution time.
        stopWatch.stop();
        System.out.println("Time: " + stopWatch.getTime());
    }

    private void doSomeTask(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
