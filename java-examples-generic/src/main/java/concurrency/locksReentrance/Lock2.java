package concurrency.locksReentrance;

/**
 * Notice how the while loop (spin lock) now also takes the thread that locked the Lock instance into consideration.
 * If either the lock is unlocked (isLocked = false) or the calling thread IS THE thread that locked the Lock instance,
 *  the while loop will not execute, and the thread calling lock() will be allowed to exit the method.

 Additionally, we need to count the number of times the lock has been locked by the same thread.
 Otherwise, a single call to unlock() will unlock the lock, even if the lock has been locked multiple times.
  We don't want the lock to be unlocked until the thread that locked it, has executed the same amount of unlock() calls as lock() calls.

The Lock class is now reentrant.
 */
public class Lock2 {

	boolean isLocked = false;
	Thread  lockedBy = null;
	int     lockedCount = 0;

	public synchronized void lock() throws InterruptedException{
		Thread callingThread = Thread.currentThread();
		while(isLocked && lockedBy != callingThread){
			wait();
		}
		isLocked = true;
		lockedCount++;
		lockedBy = callingThread;
	}


	public synchronized void unlock(){
		if(Thread.currentThread() == this.lockedBy){
			lockedCount--;
			if(lockedCount == 0){
				isLocked = false;
				notify();
			}	      
		}
	}


}
