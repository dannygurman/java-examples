package designPatterns.behavioral.Strategy.ex2;

public class MuteQuack implements QuackBehavior {
	public void quack() {
		System.out.println("<< Silence >>");
	}
}
