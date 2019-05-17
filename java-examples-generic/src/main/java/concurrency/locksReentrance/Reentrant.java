package concurrency.locksReentrance;

/**
 * Synchronized blocks in Java are reentrant.
 * This means, that if a Java thread enters a synchronized block of code,
 * and thereby take the lock on the monitor object the block is synchronized on,
 * the thread can enter other Java code blocks synchronized on the same monitor object.
 *    
 * Notice how both outer() and inner() are declared synchronized, which in Java is equivalent
 *  to a synchronized(this) block.
 *  If a thread calls outer() there is no problem calling inner() from inside outer(),
 *  since both methods (or blocks) are synchronized on the same monitor object ("this").
 *  If a thread already holds the lock on a monitor object, it has access to all blocks synchronized 
 *  on the same monitor object.
 *   This is called reentrance.
 *   The thread can reenter any block of code for which it already holds the lock.
 */
public class Reentrant {

	public synchronized void outer(){
		inner();
	}

	public synchronized void inner(){
		//do something
	}

}
