package designPatterns.compound.ex1;


//Integrate the helper Observable with the Quackable classes:
//All we need to do is make sure the Quackable classes are composed with an Observable and that they know how to delegate to it.
//After that, they are ready to be Observables

public class MallardDuck implements Quackable {
	Observable observable;
 
	public MallardDuck() {
		observable = new Observable(this);
	}
 
	public void quack() {
		System.out.println("Quack");
		notifyObservers();
	}
 
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}
 
	public void notifyObservers() {
		observable.notifyObservers();
	}
 
	public String toString() {
		return "Mallard Duck";
	}
}
