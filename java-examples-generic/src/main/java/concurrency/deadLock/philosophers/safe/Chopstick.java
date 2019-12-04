package concurrency.deadLock.philosophers.safe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private Lock lock;

    public Chopstick() {
        lock = new ReentrantLock();
    }

    public boolean pickUp() {
        //Try lock instead of lock
        return lock.tryLock();
    }

    public void putDown() {
        lock.unlock();
    }
}

