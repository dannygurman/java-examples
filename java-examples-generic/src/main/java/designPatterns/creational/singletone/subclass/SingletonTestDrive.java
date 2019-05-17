package designPatterns.creational.singletone.subclass;

public class SingletonTestDrive {
	public static void main(String[] args) {
		Singleton foo = CoolerSingleton.getInstance();
		Singleton bar = HotterSingleton.getInstance();
		System.out.println(foo);
		System.out.println(bar);

		//The function in super is called - a problem!
		foo.printBla();
		bar.printBla();

	}
}
