package designPatterns.compound.ex1;


//we need some quality control to make sure our ducks get wrapped. 
//We are going to build an entire factory just to produce them. The factory
//should produce a family of products that consists of different types of 
//ducks, so we are going to use the Abstract Factory Pattern


//We are defining an abstract factory that subclasses will implement to create different families
public abstract class AbstractDuckFactory {
 
	
	//Each method creates one kind of duck.
	public abstract Quackable createMallardDuck();
	public abstract Quackable createRedheadDuck();
	public abstract Quackable createDuckCall();
	public abstract Quackable createRubberDuck();
}
