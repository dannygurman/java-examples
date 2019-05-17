package designPatterns.behavioral.Strategy.ex1;

public class Context {
	private Strategy strategy;
	 
    // Constructor
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
 
    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }

}
