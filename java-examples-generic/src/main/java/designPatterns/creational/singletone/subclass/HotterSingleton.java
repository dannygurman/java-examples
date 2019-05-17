package designPatterns.creational.singletone.subclass;

public class HotterSingleton extends Singleton {
	// useful instance variables here
 
	private HotterSingleton() {
		super();
		System.out.println ("In HotterSingleton");
	}
 
	public void printBla() {
		System.out.println ("bla hotter");
	}
}
