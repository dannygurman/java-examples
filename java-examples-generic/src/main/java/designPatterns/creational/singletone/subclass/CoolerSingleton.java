package designPatterns.creational.singletone.subclass;

public class CoolerSingleton extends Singleton {
	// useful instance variables here
	protected static Singleton uniqueInstance;
 
	private CoolerSingleton() {
		super();
	}
 
	public void printBla() {
		System.out.print("bla coller");
	}
	
}
