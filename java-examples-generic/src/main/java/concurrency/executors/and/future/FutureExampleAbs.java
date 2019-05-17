package concurrency.executors.and.future;

import concurrency.executors.and.future.ArchiveSearcher;
import concurrency.executors.and.future.ArchiveSearcherImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class FutureExampleAbs {

    protected ExecutorService executor = Executors.newSingleThreadExecutor();
    protected ArchiveSearcher searcher = new ArchiveSearcherImpl();



    public void displayOtherThings() throws InterruptedException {
        System.out.println ("Do Other things");
        Thread.sleep(1000);
        System.out.println ("And other things..");
    }


    public void displayText(String text) throws InterruptedException {
        System.out.println (text);
    }

    public void cleanup() throws InterruptedException {
        System.out.println ("cleanup");
    }
}
