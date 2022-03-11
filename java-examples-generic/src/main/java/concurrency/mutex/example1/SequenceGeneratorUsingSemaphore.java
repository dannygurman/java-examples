package concurrency.mutex.example1;

import java.util.concurrent.Semaphore;

/*Like ReentrantLock, the Semaphore class was also introduced in Java 1.5.
While in case of a mutex only one thread can access a critical section,
Semaphore allows a FIXED NUMBER of threads to access a critical section.
Therefore, we can also implement a mutex by setting the number of allowed threads in a Semaphore to one.
*/
public class SequenceGeneratorUsingSemaphore extends SequenceGenerator {

    int permits = 1;
    private Semaphore mutex = new Semaphore(permits);

    @Override
    public int getNextSequence() {
        try {
            mutex.acquire();
            return super.getNextSequence();
        } catch (InterruptedException e) {
            // exception handling code
            return -1;
        } finally {
            mutex.release();
        }
    }
}
