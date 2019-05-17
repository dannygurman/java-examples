package consumerProducer.ex2;

import java.util.concurrent.BlockingQueue;

public class Producer  implements Runnable {

    public  static final String EXIT_MESSAGE = "exit";

    private int numOfMessages = 50;

    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> q){
        this.queue=q;
    }
    @Override
    public void run() {
        //produce messages
        for(int i=1; i <= numOfMessages; i++){
            Message msg = new Message(""+i);
            try {
                Thread.sleep(i);
                queue.put(msg);
                System.out.println("Produced "+msg.getMessageText());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //adding exit message
        Message msg = new Message(EXIT_MESSAGE);
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
