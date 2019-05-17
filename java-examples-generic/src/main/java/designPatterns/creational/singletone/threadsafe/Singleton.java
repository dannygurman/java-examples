package designPatterns.creational.singletone.threadsafe;

public class Singleton {
	
	
	private static Singleton uniqueInstance;
 
	// other useful instance variables here
 
	private Singleton() {}
 
	//By adding the synchronized keyword we force every
	//thread to wait its turn before it can enter the method.
	//No two threads could enter the method in the same time.
	
	//The problem:
	//Expensive - the only time synchronization is relevant is the first time through this method.
	//In other words, once we�ve set the uniqueInstance variable to an instance 
	//of Singleton, we have no further need to synchronize this method. After 
	//the first time through, synchronization is totally unneeded overhead!
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
 
	// other useful methods here
}
