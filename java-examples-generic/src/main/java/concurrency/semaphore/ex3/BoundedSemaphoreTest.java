package concurrency.semaphore.ex3;

public class BoundedSemaphoreTest {

    public static void main(String[] args) {
        final int bound = 5;
        MySemaphore semaphore = new BoundedSemaphore(bound);

        SendingThread sender = new SendingThread(semaphore);
        ReceivingThread receiver = new ReceivingThread(semaphore);

        new Thread(sender).start();
        new Thread(receiver).start();
    }

}
