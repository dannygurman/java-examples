package concurrency.thread.siganaling.ex1;

///
//http://tutorials.jenkov.com/java-concurrency/thread-signaling.html
//
// A thread that calls wait() on any object becomes inactive until another thread calls notify() on that object.
// //In order to call either wait() or notify the calling thread must first obtain the lock on that object.
// //In other words, the calling thread must call wait() or notify() from inside a synchronized block.
// //Here is a modified version of MySignal called MyWaitNotify that uses wait() and notify().
public class MyWaitNotify {

    MonitorObject myMonitorObject = new MonitorObject();

    // Will the waiting thread not block the notifying thread from ever entering the synchronized block in doNotify()?
    // The answer is no!.
    // Once a thread calls wait() it releases the lock it holds on the monitor object.
    // This allows other threads to call wait() or notify() too,
    // since these methods must be called from inside a synchronized block.

    //Once a thread is awakened it cannot exit the wait() call
    // until the thread calling notify() has left its synchronized block.
    // In other words:
    // The awakened thread must reobtain the lock on the monitor object before it can exit the wait() call

    //If multiple threads are awakened using notifyAll() only one awakened thread at a time can exit the wait() method,
    // since each thread must obtain the lock on the monitor object in turn before exiting wait().

    public void doWait(){
        synchronized(myMonitorObject){
            try{
                myMonitorObject.wait();
            } catch(InterruptedException e){
                //Something to do
            }
        }
    }

    public void doNotify(){
        synchronized(myMonitorObject){
            myMonitorObject.notify();
        }
    }
}
