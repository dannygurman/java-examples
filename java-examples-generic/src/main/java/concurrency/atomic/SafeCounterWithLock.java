package concurrency.atomic;

public class SafeCounterWithLock implements Counter{
    private volatile int counter;

    public int getValue() {
        return counter;
    }

    public synchronized void increment() {
        counter++;
    }
}
