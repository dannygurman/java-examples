package concurrency.thread.ex1;

//https://stackoverflow.com/questions/30471285/what-is-the-difference-between-wait-notify-and-wait-interrupt

//What is the difference between wait/notify and wait/interrupt?
//        When a thread calls notify on some monitor, it wakes up a single thread that's waiting on that monitor,
// but which thread gets woken is decided by the scheduler.
//
// (Alternatively a thread can call notifyAll, which wakes up all the threads waiting for that monitor, then they all contend for the monitor,
// then the losers go back to waiting.)
//
// That's why the target of the call is different, the notification is made to the monitor, which tells the scheduler to pick a thread to wake up.

//Unlike notify, interruption targets a specific thread.
// And interruption does NOT require that the interrupted thread be waiting on a monitor.

// For a thread to call wait on a monitor it has to have acquired that monitor first,
// then wait releases that monitor until the thread is done waiting or is interrupted.

 //Oracle's recommendation is to use interruption only for cancellation.
 // /Also the classes in java.examples.persistence.util.concurrent are designed to use interrupt for cancellation.

//In order to make this code quit once it's interrupted, rather then return to waiting, add a check for the interrupted
// flag status to the loop condition, and have the catch block set the interrupt flag (which gets reset when the exception is thrown):

public class InterruptExample {

    int x = 1;

    synchronized void someOperation () {
        synchronized (Foo.class) {
            while (x > 5 && ( ! Thread.currentThread().isInterrupted()) ) {//Some condition + check innterupt
                try {
                    Foo.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
