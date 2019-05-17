package designPatterns.compound.ex1;


//We need to make sure all Quackables implement the QuackObservable..

public interface Quackable extends QuackObservable {
	public void quack();
}
