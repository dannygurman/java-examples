package concurrency.thread.ex1;

/**
 * An interrupt is an indication to a thread that it should stop what it is doing and do something else.
 *  It's up to the programmer to decide exactly how a thread responds to an interrupt, 
 *  but it is very common for the thread to terminate. This is the usage emphasized in this lesson.
A thread sends an interrupt by invoking interrupt on the Thread object for the thread to be interrupted.
 For the interrupt mechanism to work correctly, the interrupted thread must support its own interruption.
Supporting Interruption

How does a thread support its own interruption? This depends on what it's currently doing. 
If the thread is frequently invoking methods that throw InterruptedException, 
it simply returns from the run method after it catches that exception. For example, 
suppose the central message loop in the SleepMessages example were in the run method of a thread's Runnable object. 
Then it might be modified as follows to support interrupts:

 */


public class SleepMessage2 {
	public static void main(String args[])	throws InterruptedException {

		String importantInfo[] = {
				"Mares eat oats",
				"Does eat oats",
				"Little lambs eat ivy",
				"A kid will eat ivy too"
		};

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
	}
}
