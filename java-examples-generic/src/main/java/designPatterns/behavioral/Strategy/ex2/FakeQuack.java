package designPatterns.behavioral.Strategy.ex2;

public class FakeQuack implements QuackBehavior {
	public void quack() {
		System.out.println("Qwak");
	}
}
