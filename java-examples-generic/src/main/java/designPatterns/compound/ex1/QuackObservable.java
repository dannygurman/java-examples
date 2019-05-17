package designPatterns.compound.ex1;

//QuackObservable is the interface that Quackables should implement if they want to be observed

public interface QuackObservable {
//	It has a method for registering	Observers.
	//Any object implementing the Observer interface can listen to quacks. 

	public void registerObserver(Observer observer);
	
	
	public void notifyObservers();
}
