package concurrency.atomic;


import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.junit.Test;

public class ThreadCountersTest {

    @Test
    public void givenMultiThread_when_SAFECounter_WithLockIncrement() throws InterruptedException {
        testCounterInternal(new SafeCounterWithLock());
    }

    @Test
    public void givenMultiThread_when_SAFECounter_WithoutLockIncrement() throws InterruptedException {
        testCounterInternal(new SafeCounterWithoutLock());
    }

    /**
     * This test shows the behaviour of a thread-UNSAFE class in a multithreaded scenario.     *
     * We are calling the increment methods 1000 times from a pool of 3 threads.     *
     * In most of the cases, the counter will less than 1000, because of lost updates,     *
     * however, occasionally it may reach 1000, when no threads called the method simultaneously.     *
     * This may cause the build to fail occasionally.
     */

        @Test
        public void givenMultiThread_when_UNSAFECounterIncrement() throws InterruptedException {
            testCounterInternal(new UnsafeCounter());
        }


        private void testCounterInternal(final Counter counter) throws InterruptedException {
            ExecutorService service = Executors.newFixedThreadPool(3);

            Runnable incrementTask= counter::increment;

            IntConsumer action = count -> service.submit(incrementTask);

            IntStream.range(0, 1000).forEach(action);

            service.awaitTermination(100, TimeUnit.MILLISECONDS);

            assertEquals(1000, counter.getValue());
        }

}
