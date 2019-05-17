package designPatterns.creational.singletone.dcl;

//
// Danger!  This implementation of Singleton not
// guaranteed to work prior to Java 5
//

//With double-checked locking, we first check to see if an instance is created, and if not, THEN we 
//synchronize. This way, we only synchronize the first time through, just what we want.
//
//The volatile keyword ensures that multiple threads 
//handle the uniqueInstance variable correctly when it 
//is being initialized to the Singleton instance.



public class Singleton {
	private volatile static Singleton uniqueInstance;
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
	
	public void printBla() {
		System.out.print("bla");
	}
}
