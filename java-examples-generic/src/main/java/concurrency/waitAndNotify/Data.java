package concurrency.waitAndNotify;

public class Data {
    private String packet;
    
    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;
 
    public synchronized String receive() {
        while (transfer) {
            //Transfer in progress or waiting for transfer
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.out.println("Thread Interrupted");
            }
        }
        //Transfer == false :Transfer done
        //Notify that transfer alloerf
        transfer = true;

        notifyAll();

        return packet;
    }
 
    public synchronized void send(String packet) {
        while ( ! transfer) {
            try {
                //transfer not allowed yet
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.out.println("Thread Interrupted");
            }
        }
        //Transfer allowed : transfer == true

        //Notify that transfer finished - so recivers could read
        transfer = false;
        
        this.packet = packet;
        //notify so recivers will read
        notifyAll();
    }
}