package concurrency.locksReentrance;

/**
 * The lock implementation shown in ** Lock1 ** is not reentrant.
 * If we rewrite the Reentrant class like below, the thread calling outer() will be blocked
 *  inside the lock.lock() in the inner() method.
 *  
 *  A thread calling outer() will first lock the Lock instance.
 *  Then it will call inner(). Inside the inner() method the thread will again try to lock the Lock instance.
 *  This will fail (meaning the thread will be blocked), since the Lock instance was 
 *  locked already in the outer() method.

  The reason the thread will be blocked the second time it calls lock() without having called unlock() in between,
   is apparent when we look at the lock() implementation of Lock1 *  
 *  It is the condition inside the while loop (spin lock) that determines if a thread is allowed 
 *  to exit the lock() method or not.
 *   Using Lock1 - the condition is that isLocked must be false for this to be allowed,regardless of what thread locked it.
 *   
 *   Lock2 class is reentrant.
 *  
 *  
 * @author DannyG
 *
 */
public class Reentrant2 {

	  Lock2 lock = new Lock2();

	  public void outer() throws Exception {
	    lock.lock();
	    inner();
	    lock.unlock();
	  }

	  public synchronized void inner  () throws Exception{
	    lock.lock();
	    //do something
	    lock.unlock();
	  }
	}
