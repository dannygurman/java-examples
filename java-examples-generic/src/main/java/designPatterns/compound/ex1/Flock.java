package designPatterns.compound.ex1;

import java.util.Iterator;
import java.util.ArrayList;

//The Composite Pattern that allows us to treat a collection of objects in the same way as individual objects.

//Flock -

//The composite needs to implement the same interface as the leaf elements. Our leaf elements are Quackables.

public class Flock implements Quackable {
	
//	We are using an ArrayList inside each Flock to hold the Quackables that belong to the Flock.
	ArrayList ducks = new ArrayList();
  
	public void add(Quackable duck) {
		ducks.add(duck);
	}
  
	
	//After all, the Flock is a Quackable too
	//The quack() method in Flock needs to work over the entire Flock. 
	//Here	we iterate through the ArrayList and call quack() on each element.
	public void quack() {
		Iterator iterator = ducks.iterator();
		while (iterator.hasNext()) {
			Quackable duck = (Quackable)iterator.next();
			duck.quack();
		}
	}
   
	
	
	public void registerObserver(Observer observer) {
		Iterator iterator = ducks.iterator();
		while (iterator.hasNext()) {
			Quackable duck = (Quackable)iterator.next();
			duck.registerObserver(observer);
		}
	}
  
	public void notifyObservers() { }
  
	public String toString() {
		return "Flock of Ducks";
	}
}
