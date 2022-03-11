package concurrency.mutex.example1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MutexTest {


    @Test
    public void givenUnsafeSequenceGenerator_whenRaceCondition_thenUnexpectedBehavior() throws Exception {
        /**
         * THIS TEST WILL FAIL MOST OF THE TIME, FOR EXAMPLE:
         * java.lang.AssertionError:
         * Expected :1000
         * Actual   :992
         *
         * The uniqueSequences is supposed to have the size equal to the number of times we've
         * executed the getNextSequence method in our test case.
         *
         * However, this is not the case because of the race condition.
         * Obviously, we don't want this behavior.
         *
         * So, to avoid such race conditions, we need to make sure that only one thread can execute the
         * getNextSequence method at a time.
         * In such scenarios, we can use a mutex to synchronize the threads.
         * @throws Exception
         */

        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        internalSequenceTest(sequenceGenerator);

    }


    @Test
    public void givenSynchronizedMethodSequenceGenerator_whenRaceCondition_thenBehaviorAsExpected() throws Exception {
        SequenceGenerator sequenceGenerator = new SequenceGeneratorUsingSynchronizedMethod();
        internalSequenceTest(sequenceGenerator);
    }


    @Test
    public void givenSynchronizedBlockSequenceGenerator_whenRaceCondition_thenBehaviorAsExpected() throws Exception {
        SequenceGenerator sequenceGenerator = new SequenceGeneratorUsingSynchronizedBlock();
        internalSequenceTest(sequenceGenerator);
    }

    @Test
    public void givenUsingReentrantLockSequenceGenerator_whenRaceCondition_thenBehaviorAsExpected() throws Exception {
        SequenceGenerator sequenceGenerator = new SequenceGeneratorUsingReentrantLock();
        internalSequenceTest(sequenceGenerator);
    }


    @Test
    public void givenUsingSemaphoreGenerator_whenRaceCondition_thenBehaviorAsExpected() throws Exception {
        SequenceGenerator sequenceGenerator = new SequenceGeneratorUsingSemaphore();
        internalSequenceTest(sequenceGenerator);
    }


    @Test
    public void givenUsingMonitorGenerator_whenRaceCondition_thenBehaviorAsExpected() throws Exception {
        SequenceGenerator sequenceGenerator = new SequenceGeneratorUsingMonitor();
        internalSequenceTest(sequenceGenerator);
    }



    private void internalSequenceTest(SequenceGenerator sequenceGenerator) throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(sequenceGenerator, count);
        Assert.assertEquals(count, uniqueSequences.size());
    }


    private Set<Integer> getUniqueSequences(SequenceGenerator generator, int count) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Set<Integer> uniqueSequences = new LinkedHashSet<>();
        List<Future<Integer>> futures = new ArrayList<>();

        Callable<Integer> getNextInstanceTask = generator::getNextSequence;
        for (int i = 0; i < count; i++) {
            futures.add(executor.submit(getNextInstanceTask));
        }

        for (Future<Integer> future : futures) {
            uniqueSequences.add(future.get());
        }

        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();

        return uniqueSequences;
    }
}
