package concurrency.mutex.example1;

import java.util.concurrent.locks.ReentrantLock;

public class SequenceGeneratorUsingReentrantLock extends SequenceGenerator {

    //The ReentrantLock class was introduced in Java 1.5.
    // It provides more flexibility and control than the
    // synchronized keyword approach.

    private ReentrantLock mutex = new ReentrantLock();

    @Override
    public int getNextSequence() {
        try {
            mutex.lock();
            return super.getNextSequence();
        } finally {
            mutex.unlock();
        }
    }
}