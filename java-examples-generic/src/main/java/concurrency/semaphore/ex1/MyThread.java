package concurrency.semaphore.ex1;

// java program to demonstrate
// use of semaphores Locks

import java.util.concurrent.Semaphore;

class MyThread extends Thread {
    Semaphore sem;
    String threadName;
    public MyThread(Semaphore sem, String threadName)  {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        // run by thread A
        if(this.getName().equals("A"))        {
            performOperation(()-> Shared.count++);
        }

        // run by thread B
        else   {
            performOperation(()-> Shared.count--);
        }
    }

    public void performOperation(Runnable runnable){
        System.out.println("Starting " + threadName);
        try
        {
            // First, get a permit.
            System.out.println(threadName + " is waiting for a permit.");

            // acquiring the lock
            sem.acquire();

            System.out.println(threadName + " gets a permit.");

            // Now, accessing the shared resource.
            // other waiting threads will wait, until this
            // thread release the lock
            for(int i=0; i < 5; i++){
                runnable.run();
                System.out.println(threadName + ": " + Shared.count);

                // Now, allowing a context switch -- if possible.
                // for other thread  to execute
                Thread.sleep(1000);
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        // Release the permit.
        System.out.println(threadName + " releases the permit.");
        sem.release();
    }
}
