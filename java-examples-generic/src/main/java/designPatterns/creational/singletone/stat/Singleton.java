package designPatterns.creational.singletone.stat;

//Eagerly created instance rather than a lazily created one.

//If your application always creates and uses an instance of the Singleton or the overhead of 
//factorymethods and runtime aspects of the Singleton are not onerous, you may want to create your
//Singleton eagerly, like this:

//Using this approach, we rely on the JVM to create the unique instance of the Singleton when 
//the class is loaded. The JVM guarantees that the instance will be created before any thread 
//accesses the static uniqueInstance variable. 

public class Singleton {
	private static Singleton uniqueInstance = new Singleton();

	private Singleton() {}

	public static Singleton getInstance() {
		return uniqueInstance;
	}

	public void printBla() {
		System.out.print("bla");
	}

}
