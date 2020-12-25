package concurrency.semaphore.ex3;

public class SendingThread implements Runnable{
    private SimpleSemaphore semaphore = null;

    public SendingThread(SimpleSemaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        while(true){
            //do something, then signal
            System.out.println("Sending - before take ");
            this.semaphore.take();
            System.out.println("Sending - after take ");

        }
    }
}
