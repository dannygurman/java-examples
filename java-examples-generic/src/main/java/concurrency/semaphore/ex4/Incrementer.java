package concurrency.semaphore.ex4;

public class Incrementer implements Runnable {
    private static BinarySemaphore semaphore = null;

    public Incrementer(BinarySemaphore s) {
        semaphore = s;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Incrementing...");
            semaphore.increment();
        }
    }
}
