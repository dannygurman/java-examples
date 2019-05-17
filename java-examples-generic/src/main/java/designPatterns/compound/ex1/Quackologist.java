package designPatterns.compound.ex1;

//We need to implement the Observable interface or else we wont be able to register with a QuackObservable
public class Quackologist implements Observer {
 
	public void update(QuackObservable duck) {
		System.out.println("Quackologist: " + duck + " just quacked.");
	}
 
	public String toString() {
		return "Quackologist";
	}
}
