package concurrency.semaphore.ex3;

public class CountingSemaphoreTest {

    public static void main(String[] args) {
        MySemaphore semaphore = new CountingSemaphore();

        SendingThread sender = new SendingThread(semaphore);
        ReceivingThread receiver = new ReceivingThread(semaphore);

        new Thread(sender).start();
        new Thread(receiver).start();
    }

}
