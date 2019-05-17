package designPatterns.creational.factory.factoryMethod.ex2;

public class Client 
{
	public static void main( String arg[] ) 
	{
		Creator creator = new ConcreteCreator();
		creator.anOperation();
	}
}