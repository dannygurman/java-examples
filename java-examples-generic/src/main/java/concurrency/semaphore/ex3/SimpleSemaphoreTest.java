package concurrency.semaphore.ex3;

public class SimpleSemaphoreTest {

    public static void main(String[] args) {
        SimpleSemaphore semaphore = new SimpleSemaphore();

        SendingThread sender = new SendingThread(semaphore);
        ReceivingThread receiver = new ReceivingThread(semaphore);

        new Thread(sender).start();
        new Thread(receiver).start();
    }
}
