package designPatterns.creational.singletone.subclass;

// constructor is not private public 
//so  it�s not really
//a Singleton anymore, because other 
//classes can instantiate it


//Also -The implementation of Singleton is based on a static variable, 
//so if you do a straightforward subclass, all of your //derived classes will share the same instance variable. 
//So, for subclassing to work, implementing registry of sorts is required in the base class.

//Better to add add the singletone pattern in each subclass.

public  class Singleton {
	protected static Singleton uniqueInstance;

	// other useful instance variables here

	protected Singleton() {}

	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}

	public void printBla() {
		System.out.println("bla super");
	}
	
}
