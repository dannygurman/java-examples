package designPatterns.creational.factory.factoryMethod.ex2;

public class ConcreteCreator extends Creator 
{
	protected Product factoryMethod() //Implementing the factory method
	{
		return new ConcreteProduct();
	}
}
