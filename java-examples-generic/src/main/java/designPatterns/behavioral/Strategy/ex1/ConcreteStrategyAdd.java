package designPatterns.behavioral.Strategy.ex1;

public class ConcreteStrategyAdd implements Strategy {
 
    public int execute(int a, int b) {
        System.out.println("Called ConcreteStrategyAdd's execute()");
        return a + b;  // Do an addition with a and b
    }

}
