package designPatterns.compound.ex1;

public class CountingDuckFactory extends AbstractDuckFactory {
//  
//	Each method creates a product: a particular kind of Quackable. 
//		The actual product is unknownto the simulator - it just knowsits getting a Quackable
	
	
//	Each method wraps the uackable with the quack counting decorator.
	//The simulator will never know the difference -it just gets back a Quackable. 
//	But now we can  be sure that all quacks are being counted.
	public Quackable createMallardDuck() {
		return new QuackCounter(new MallardDuck());
	}
  
	public Quackable createRedheadDuck() {
		return new QuackCounter(new RedheadDuck());
	}
  
	public Quackable createDuckCall() {
		return new QuackCounter(new DuckCall());
	}
   
	public Quackable createRubberDuck() {
		return new QuackCounter(new RubberDuck());
	}
}
