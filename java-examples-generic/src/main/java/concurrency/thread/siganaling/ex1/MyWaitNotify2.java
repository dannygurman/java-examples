package concurrency.thread.siganaling.ex1;

//Missed Signals:
//The methods notify() and notifyAll() do not save the method calls to them in case no threads are
// waiting when they are called.
//
// The notify signal is then just lost.
//
// //Therefore, if a thread calls notify() before the thread to signal has called wait(),
// //the signal will be missed by the waiting thread.
//
// //This may or may not be a problem, but in some cases this may result in the waiting thread waiting forever,
// //never waking up, because the signal to wake up was missed.

// To avoid losing signals they should be stored inside the signal class.
// //In the MyWaitNotify example the notify signal should be stored in a member variable inside the MyWaitNotify instance.
//
// //Here is a modified version of MyWaitNotify that does this:

//Notice how the doNotify() method now sets the wasSignalled variable to true before calling notify().
// Also, notice how the doWait() method now checks the wasSignalled variable before calling wait().
// //In fact it only calls wait() if no signal was received in between the previous doWait() call and this.

public class MyWaitNotify2 {

    MonitorObject myMonitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void doWait(){
        synchronized(myMonitorObject){
            if( ! wasSignalled){
                try{
                    myMonitorObject.wait();
                } catch(InterruptedException e){
                    //Something
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
