package concurrency.semaphore.ex4;

public class BinarySemaphore {
    private static BinarySemaphore semaphore;
    private static int resource = 1;

    private BinarySemaphore(){}

    public synchronized void increment(){
        while(isAvailable()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resource += 1;

        report();

        this.notifyAll();
    }

    public synchronized void decrement(){
        while( ! isAvailable()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        resource -= 1;

        report();

        this.notifyAll();
    }

    public synchronized final static boolean isAvailable(){
        return resource == 1 ? true : false;
    }

    public synchronized final static void report(){
        System.out.println("Resource value: " + resource);
    }

    public final static BinarySemaphore getInstance(){
        if(semaphore == null){
            semaphore = new BinarySemaphore();
        }
        return semaphore;
    }
}