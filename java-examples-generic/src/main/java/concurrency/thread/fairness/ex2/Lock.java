package concurrency.thread.fairness.ex2;

//A simple implementation of the Lock class could look like this:
//If you look at the Synchronizer class above and look into this Lock implementation you will notice that threads are now blocked
// trying to access the lock() method, if more than one thread calls lock() simultaneously.
//
// Second, if the lock is locked, the threads are blocked in the wait() call inside the while(isLocked) loop in the lock() method.
// Remember that a thread calling wait() releases the synchronization lock on the Lock instance, so threads waiting to enter lock() can now do so.
//
// The result is that multiple threads can end up having called wait() inside lock().

public class Lock {
    private boolean isLocked      = false;
    private Thread  lockingThread = null;

    public synchronized void lock() throws InterruptedException{
        while(isLocked){
            wait();
        }
        isLocked      = true;
        lockingThread = Thread.currentThread();
    }

    public synchronized void unlock(){
        if(this.lockingThread != Thread.currentThread()){
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked      = false;
        lockingThread = null;
        notify();
    }

}
