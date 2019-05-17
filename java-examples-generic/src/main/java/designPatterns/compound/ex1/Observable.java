package designPatterns.compound.ex1;

import java.util.Iterator;
import java.util.ArrayList;


//We need to make sure all the concrete classes that implement Quackable can handle being a QuackObservable.
//We could approach this by implementing registration and notification in each and every class (like we did in previous example) 
//But we are going to do it a little differently this time:
//We are going to encapsulate the registration and notification code in another class, call it Observable, and compose it with a QuackObservable.
//That way we only write the real code once and the QuackObservable just needs enough code to delegate to the helper class Observable.

//Observable implements all the functionality a Quackable needs to be an observable. 
//We just need to plug it into a class and have that class delegate to Observable.

public class Observable implements QuackObservable {
	ArrayList observers = new ArrayList();
	QuackObservable duck;


	//	In the constructor we get passed the QuackObservable  that is using this object to manage its observable behavior. 
	//	Check out the notify() method below; you�ll see that when a notify occurs, Observable passes 
	//	this object along so that the observer knows which object is quacking.
	public Observable(QuackObservable duck) {
		this.duck = duck;
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers() {
		Iterator iterator = observers.iterator();
		while (iterator.hasNext()) {
			Observer observer = (Observer)iterator.next();
			observer.update(duck);
		}
	}

	public Iterator getObservers() {
		return observers.iterator();
	}
}
