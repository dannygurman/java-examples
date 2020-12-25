package concurrency.semaphore.ex3;

public class ReceivingThread implements Runnable {
    private SimpleSemaphore semaphore = null;

    public ReceivingThread(SimpleSemaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        while(true){
            System.out.println("ReceivingThread - before release ");
            try {
                this.semaphore.release();
            } catch (InterruptedException e) {
                System.out.println("ReceivingThread interrupted");
                e.printStackTrace();
                Thread.currentThread().interrupt();
                /*calling interrupt - First it simply sets an internal interrupted-flag and
                then it checks if the thread that
                it was called on is currently blocking on an activity
                like wait(), sleep(), or join().
                If it finds one, then it wakes up that method and causes that method
                  to throw the exception inside the thread it was called on (not from).*/
                return;
            }
            System.out.println("ReceivingThread - after release ");
            //receive signal, then do something...
        }
    }
}
