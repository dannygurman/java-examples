package concurrency.thread.ex4;

//https://www.yegor256.com/2015/10/20/interrupted-exception.html
//https://dzone.com/articles/how-to-handle-the-interruptedexception


 //There are two methods that are used in this example.
 // //When I call loop.interrupt(), a flag is set to true somewhere inside the thread loop.
 // //When I call interrupted(), the flag is returned and immediately set to false.
 // //Yeah, that's the design of the method. It checks the flag, returns it, and sets it to false

//Thus, if I never call Thread.interrupted() inside the thread and don't exit when the flag is true,
// //nobody will be able to stop me.
// //Literally, I will just ignore their calls to interrupt().
// They will ask me to stop, but I will ignore them. They won't be able to interrupt me.
public class ThreadInterruptExample4a {

    public static void main(String[] args) {
        Thread loop = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (Thread.interrupted()) {
                                break;
                            }
                            // Continue to do nothing
                        }
                    }
                }
        );
        loop.start();
        loop.interrupt();
    }


}
