package spring.examples.async2.tests;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import spring.examples.async2.AsyncComponent;
import spring.examples.async2.config.AsyncConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AsyncConfig.class }, loader = AnnotationConfigContextLoader.class)
public class AsyncExampleIntegrationTest {

    @Autowired
    private AsyncComponent asyncComponentExample;

    // tests

    @Test
    public void testAsyncAnnotationForMethodsWithVoidReturnType() {
        System.out.println("Start - invoking an asynchronous method. " + Thread.currentThread().getName());
        asyncComponentExample.asyncMethodWithVoidReturnType();
        System.out.println("End - invoking an asynchronous method. ");
    }

    @Test
    public void testAsyncAnnotationForMethodsWithReturnType() throws InterruptedException, ExecutionException {
        System.out.println("Start - invoking an asynchronous method. " + Thread.currentThread().getName());
        final Future<String> future = asyncComponentExample.asyncMethodWithReturnType();

        while (true) {
            if (future.isDone()) {
                System.out.println("Result from asynchronous process - " + future.get());
                break;
            }
            System.out.println("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }

    @Test
    public void testAsyncAnnotationForMethodsWithConfiguredExecutor() {
        System.out.println("Start - invoking an asynchronous method. ");
        asyncComponentExample.asyncMethodWithConfiguredExecutor();
        System.out.println("End - invoking an asynchronous method. ");
    }

    @Test
    public void testAsyncAnnotationForMethodsWithException() throws Exception {
        System.out.println("Start - invoking an asynchronous method. ");
        asyncComponentExample.asyncMethodWithExceptions();
        System.out.println("End - invoking an asynchronous method. ");
    }

}

