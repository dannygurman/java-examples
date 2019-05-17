package concurrency.locks;

/** http://tutorials.jenkov.com/java-concurrency/locks.html 
 * 
 * When the thread is done with the code in the critical section (the code between lock() and unlock()), 
 * the thread calls unlock().
 *  Executing unlock() sets isLocked back to false, and notifies (awakens) one of the threads
 *   waiting in the wait()  call in the lock() method, if any.**/

public class Counter2 {

	private Lock1 lock = new Lock1();
	private int count = 0;

	public int inc() throws InterruptedException{
		lock.lock();
		int newCount = ++count;
		lock.unlock();
		return newCount;
	}
	
}
