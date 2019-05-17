package designPatterns.behavioral.Strategy.ex2;

public class ModelDuck extends Duck {
	public ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}

	public void display() {
		System.out.println("I'm a model duck");
	}
}
