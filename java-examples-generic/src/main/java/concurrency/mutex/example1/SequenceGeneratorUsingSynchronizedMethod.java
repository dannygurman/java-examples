package concurrency.mutex.example1;

public class SequenceGeneratorUsingSynchronizedMethod  extends  SequenceGenerator{

    /* First, we'll discuss the synchronized keyword, which is the simplest way to implement a mutex in Java.
    Every object in Java has an INTRINSIC lock associated with it.

   The synchronized method and the synchronized block use this intrinsic lock to restrict the
     access of the critical section to only one thread at a time.

    Therefore, when a thread invokes a synchronized method or enters a synchronized block,
     it automatically acquires the lock.

      The lock releases when the method or block completes or an exception is thrown from them.

    */
    @Override
    public synchronized int getNextSequence() {
        return super.getNextSequence();
    }
}
