package concurrency.thread.fairness.ex1;

//While it is not possible to implement 100% fairness in Java we can still implement our synchronization
// constructs to increase fairness between threads.

//First lets study a simple synchronized code block:

//If more than one thread call the doSynchronized() method, some of them will be blocked until the first thread
// granted access has left the method.

// //If more than one thread are blocked waiting for access there is NO guarantee about which thread is granted access next.


public class Synchronizer {

    public synchronized void doSynchronized(){
        //do a lot of work which takes a long time
    }
}
