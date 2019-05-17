package concurrency.thread.ex4;

/*
//https://www.yegor256.com/2015/10/20/interrupted-exception.html
//https://dzone.com/articles/how-to-handle-the-interruptedexception

//The code should either be bullet-fast or interruption-ready, nothing in between.


// Now, there are some methods in JDK that check the flag for us and throw InterruptedException if it is set.
// For example, this is how the method Thread.sleep() is designed (taking a very primitive approach):

public static void sleep(long millis)
        throws InterruptedException {
        while (*//* You still need to wait *//*) {
        if (Thread.interrupted()) {
        throw new InterruptedException();
        }
        // Keep waiting
        }
        }


Thread.interrupted() not only returns the flag but also sets it to false.
 Thus, once InterruptedException is thrown, the flag is reset.
  The thread no longer knows anything about the interruption request sent by the owner.

  The owner of the thread asked us to stop, Thread.sleep() detected that request, removed it, and threw InterruptedException.
  If you call Thread.sleep(), again, it will not know anything about that interruption request and will not throw anything

It's very important not to lose that InterruptedException.
 We can't just swallow it and move on.
 That would be a severe violation of the entire Java multi-threading idea.
  Our owner (the owner of our thread) is asking us to stop, and we just ignore it.
  That's a very bad idea.

If we catch and true runtime exception - t looks logical, but it doesn't guarantee that the higher level will actually stop everything and exit.
 They may just catch a runtime exception there, and the thread will remain alive.
 The owner of the thread will be disappointed.




        */

public class ThreadInterruptExample4b {
    public static void main(String[] args) {
        Thread loop = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt(); // Here! setting the flag
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
        loop.start();
        loop.interrupt();
    }

   // We're setting the flag back to true!

   // Now, nobody will blame us for having an irresponsible attitude toward a valuable flag.
    // //We found it in true status, cleared it, set it back to true, and threw a runtime exception. What happens next, we don't care.

   // I think that's it.
    // //You can find a more detailed and official description of this problem here: Java Theory and Practice: Dealing With InterruptedException.
}
