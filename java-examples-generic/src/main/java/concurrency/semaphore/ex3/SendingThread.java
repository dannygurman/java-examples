package concurrency.semaphore.ex3;

public class SendingThread implements Runnable{
    private MySemaphore semaphore = null;

    public SendingThread(MySemaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        while(true){
            //do something, then signal
            System.out.println("Sending - before take ");
            try {
                this.semaphore.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println("Sending - after take ");

        }
    }
}
