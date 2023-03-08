package concurrency.atomic;

public class UnsafeCounter implements Counter {

    private int counter;

    public int getValue() {
        return counter;
    }

    public void increment() {
        counter++;
    }
}
