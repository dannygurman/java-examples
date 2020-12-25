package concurrency.semaphore.ex4;

public class Decrementer implements Runnable{
    private static BinarySemaphore semaphore = null;

    public Decrementer(BinarySemaphore s) {
        semaphore = s;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("Decrementing...");
            semaphore.decrement();
        }
    }
}
