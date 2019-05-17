package concurrency.executors.and.future.ex1;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleExecutorExample {

    @Test
    public void testSimpleExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Hello World"));
    }
}
