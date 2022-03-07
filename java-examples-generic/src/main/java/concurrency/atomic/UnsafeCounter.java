package concurrency.atomic;

public class UnsafeCounter {

    private int counter;

    int getValue() {
        return counter;
    }

    void increment() {
        counter++;
    }
}
