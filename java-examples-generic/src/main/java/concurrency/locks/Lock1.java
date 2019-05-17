package concurrency.locks;

/** Notice the while(isLocked) loop, which is also called a "spin lock"
 *  Spin locks and the methods wait() and notify() are part of Thread Signaling.
 *
 *  While isLocked is true, the thread calling lock() is parked waiting in the wait() call.
 *
 *  In case the thread should return unexpectedly from the wait() call without
 *  having received a notify() call (Also known as  a Spurious Wakeup)
 *  the thread  re-checks the isLocked condition to see if it is safe to proceed or not,
 *  rather than just assume that being awakened means it is safe to proceed.
 *
 *   If isLocked is false, the thread exits the while(isLocked) loop,
 *   and sets isLocked back to true, to lock the Lock
 *   instance for other threads calling lock().
 * 
 * 
 * **/
public class Lock1 {

	private boolean isLocked = false;

	public synchronized void lock()	 throws InterruptedException{
		while(isLocked){
			wait();
		}
		isLocked = true;
	}

	public synchronized void unlock(){
		isLocked = false;
		notify();
	}
}
