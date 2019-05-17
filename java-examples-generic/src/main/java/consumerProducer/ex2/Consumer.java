package consumerProducer.ex2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> q){
        this.queue=q;
    }


    @Override
    public void run() {
        try{
            Message msg;
            //consuming messages until exit message is received
            while( ! (msg = queue.take()).getMessageText().equalsIgnoreCase(Producer.EXIT_MESSAGE)){
                Thread.sleep(10);
                System.out.println("Consumed "+msg.getMessageText());
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
