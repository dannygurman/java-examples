package concurrency.semaphore.ex3;

public interface MySemaphore {

    void take() throws InterruptedException;

    void release() throws InterruptedException;
}
