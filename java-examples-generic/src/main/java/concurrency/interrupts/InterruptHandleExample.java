package concurrency.interrupts;

/*
 * An interrupt is an indication to a thread that it should stop what it is doing and do something else. 
 * It's up to the programmer to decide exactly how a thread responds to an interrupt,
 *  but it is very common for the thread to terminate. This is the usage emphasized in this lesson.


A thread sends an interrupt by invoking interrupt on the Thread object for the thread to be interrupted. 
For the interrupt mechanism to work correctly, the interrupted thread must support its own interruption.


 */
public class InterruptHandleExample implements Runnable {

	static String  importantInfo[] = {"a"};

	public  void run() {
		/*
		 * How does a thread support its own interruption? 
		 * This depends on what it's currently doing.
		 * 
		 * If the thread is frequently invoking methods that throw InterruptedException, 
		 * it simply returns from the run method after it catches that exception.
		 *  
		 *  /*
		 * Many methods that throw InterruptedException, such as sleep, are designed to
		 *  cancel their current operation and return immediately when an interrupt is received.

		 *  For example:
		 */

		for (int i = 0; i < importantInfo.length; i++) {
			// Pause for 4 seconds
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// We've been interrupted: no more messages.
				return;
			}
			// Print a message
			System.out.println(importantInfo[i]);
		} 

		/* What if a thread goes a long time without invoking a method  that throws InterruptedException?
		 *  Then it must periodically invoke Thread.interrupted, 
		 *  which returns true if an interrupt has been received. 
		 */
		for (int i = 0; i < importantInfo.length; i++) {
			heavyCrunch(importantInfo[i]);
			if (Thread.interrupted()) {
				// We've been interrupted: no more crunching.
				return;
				//Another option is: throw new InterruptedException();
			}
		}

		/* The interrupt mechanism is implemented using an internal flag known as the interrupt status.
		 *  Invoking Thread.interrupt sets this flag.
		 *   When a thread checks for an interrupt by invoking the static method Thread.interrupted,
		 *   interrupt status is cleared. 
		 *    
		 *    The non-static isInterrupted method, which is used by one thread to query the interrupt status of another,
		 *     does not change the interrupt status flag.
		 */

		/*
		 * By convention, any method that exits by throwing an InterruptedException clears interrupt status when it does so.
		 *  However, it's always possible that interrupt status will immediately be set again,
		 *   by another thread invoking interrupt
		 */
	}


		static void heavyCrunch(String x){

		};
	}
