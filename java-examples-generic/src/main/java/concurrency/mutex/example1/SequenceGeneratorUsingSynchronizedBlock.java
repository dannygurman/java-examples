package concurrency.mutex.example1;

public class SequenceGeneratorUsingSynchronizedBlock extends SequenceGenerator {
    //The synchronized block is similar to the synchronized method,
    // with more control over the critical section and the object we can use for locking.

    private Object mutex = new Object();

    @Override
    public int getNextSequence() {
        synchronized (mutex) {
            return super.getNextSequence();
        }
    }
}
