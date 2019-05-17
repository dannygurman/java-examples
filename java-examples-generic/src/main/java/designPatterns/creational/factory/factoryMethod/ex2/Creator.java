package designPatterns.creational.factory.factoryMethod.ex2;

public abstract class Creator 
{
	public void anOperation() 
	{
		Product product = factoryMethod();
		product.doX();
	}
	
	protected abstract Product factoryMethod();
}
