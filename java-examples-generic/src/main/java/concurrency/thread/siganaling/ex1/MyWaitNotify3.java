package concurrency.thread.siganaling.ex1;

//Spurious Wakeups
//For inexplicable reasons it is possible for threads to wake up even if notify() and notifyAll() has not been called. T
// his is known as spurious wakeups -  Wakeups without any reason.

//If a spurious wakeup occurs in the MyWaitNofity2 class's doWait() method the waiting thread may continue processing without having
// received a proper signal to do so! This could cause serious problems in your application.

// To guard against spurious wakeups the signal member variable is checked inside a while loop instead of inside an if-statement.
// Such a while loop is also called a spin lock.

// The thread awakened spins around until the condition in the spin lock (while loop) becomes false.
// Here is a modified version of MyWaitNotify2 that shows this:

//Notice how the wait() call is now nested inside a while loop instead of an if-statement.
// //If the waiting thread wakes up without having received a signal, the wasSignalled member will still be false,
// and the while loop will execute once more, causing the awakened thread to go back to waiting.

public class MyWaitNotify3 {

    MonitorObject myMonitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void doWait(){
        synchronized(myMonitorObject){
            while(!wasSignalled){
                try{
                    myMonitorObject.wait();
                } catch(InterruptedException e){

                }
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify(){
        synchronized(myMonitorObject){
            wasSignalled = true;
            myMonitorObject.notify();
        }
    }
}
