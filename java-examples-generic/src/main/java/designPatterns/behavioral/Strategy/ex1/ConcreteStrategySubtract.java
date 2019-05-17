package designPatterns.behavioral.Strategy.ex1;

public class ConcreteStrategySubtract implements Strategy {

	public int execute(int a, int b) {
		System.out.println("Called ConcreteStrategySubtract's execute()");
		return a - b;  // Do a subtraction with a and b
	}


}
