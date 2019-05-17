package concurrency.forkJoin.ex1;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class FactorialTestCalculatorTest {

    @Test
    public void testFactorialTestCalculator() {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        FactorialSquareCalculator calculator = new FactorialSquareCalculator(3);

        forkJoinPool.execute(calculator);

        try {
            int result = calculator.get();
            System.out.print("results:" + result);//1+4 + 9 = 14
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
