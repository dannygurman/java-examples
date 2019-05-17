package concurrency.thread.ex1;


public class SleepMessage3 {
	
	/*
	 * Many methods that throw InterruptedException, such as sleep, are designed to cancel their current operation
	 *  and return immediately when an interrupt is received.
What if a thread goes a long time without invoking a method that throws InterruptedException? 
Then it must periodically invoke Thread.interrupted, which returns true if an interrupt has been received. 
For example:
	 */
	public static void main(String args[])	 {
		String inputs[] = {	"x"	};
		for (int i = 0; i < inputs.length; i++) {
			heavyCrunch(inputs[i]);
			if (Thread.interrupted()) {
				// We've been interrupted: no more crunching.
				return;
			}
		}
	}

	static void heavyCrunch(String str){

	}
}

/* In this simple example, the code simply tests for the interrupt and exits the thread if one has been received.
 *  In more complex applications,  it might make more sense to throw an InterruptedException:
 if (Thread.interrupted()) {
    throw new InterruptedException();
}
This allows interrupt handling code to be centralized in a catch clause.


The Interrupt Status Flag
The interrupt mechanism is implemented using an internal flag known as the interrupt status. 
Invoking Thread.interrupt sets this flag. 
When a thread checks for an interrupt by invoking the static method Thread.interrupted,interrupt status is cleared.
 
 The non-static isInterrupted method, which is used by one thread to query the interrupt status of another, 
 does not change the interrupt status flag.

By convention, any method that exits by throwing an InterruptedException clears interrupt status when it does so.
 However, it's always possible that 
 interrupt status will immediately be set again, by another thread invoking interrupt.

*/