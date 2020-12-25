package concurrency.semaphore.ex4;

public class BinarySemaphoreRunner {
    public static void main(String[] args) {
        Thread iIncrement = new Thread(new Incrementer(BinarySemaphore.getInstance()));
        Thread iDecrement = new Thread(new Decrementer(BinarySemaphore.getInstance()));

        iIncrement.start();
        iDecrement.start();
    }
}

