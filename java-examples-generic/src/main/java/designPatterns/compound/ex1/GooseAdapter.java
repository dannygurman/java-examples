package designPatterns.compound.ex1;

//an Adapter implements the target interface, which in this case is Quackable.
//When quack is called, the call is delegated to the goose is honk() method.

public class GooseAdapter implements Quackable {
	Goose goose;
	Observable observable;

	public GooseAdapter(Goose goose) {//The constructor takes the goose we are going to adapt.
		this.goose = goose;
		observable = new Observable(this);
	}
 
	public void quack() {//When quack is called, the call is delegated 	to the goose�s honk() method
		goose.honk();
		notifyObservers();
	}

	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	public void notifyObservers() {
		observable.notifyObservers();
	}

	public String toString() {
		return "Goose pretending to be a Duck";
	}
}
