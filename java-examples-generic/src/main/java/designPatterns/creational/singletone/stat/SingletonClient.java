package designPatterns.creational.singletone.stat;

public class SingletonClient {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		
		singleton.printBla();
	}
}
