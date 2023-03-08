package concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounterWithoutLock implements Counter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public int getValue() {
        return counter.get();
    }

    public void increment() {
        while(true) {
            int existingValue = getValue();
            int newValue = existingValue + 1;

            //Atomically sets the value to newValue if the current value == expectedValue,
            boolean succeeded = counter.compareAndSet(existingValue, newValue);
            if(succeeded) {
                return;
            }
        }
    }
}
