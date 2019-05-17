package concurrency.forkJoin.ex1;

import java.util.concurrent.RecursiveTask;

public class FactorialSquareCalculator  extends RecursiveTask<Integer> {

    //Need to calculate n^2 + (n-1)^2 + (n-2)^3
    private Integer n;

    public FactorialSquareCalculator(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        FactorialSquareCalculator calculator  = new FactorialSquareCalculator(n - 1);

        calculator.fork();

        return n * n + calculator.join();
    }
}
