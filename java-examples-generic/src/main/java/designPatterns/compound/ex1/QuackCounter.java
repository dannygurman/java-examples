package designPatterns.compound.ex1;

//Let�s create a decorator that gives the ducks some new behavior (the behavior of counting) by wrapping them with a 
//decorator object. We wont have to change the Duck code at all.


//QuackCounter is a decorator

//Like with Adapter, we need to implement the target interface.
public class QuackCounter implements Quackable {
	
	Quackable duck;//We have got an instance variable to hold on to the quacker we re decorating
	static int numberOfQuacks;//And we are counting ALL 	quacks, so we ll use a static	variable to keep track.
  
	public QuackCounter(Quackable duck) {//We get the reference to the Quackable we�re decorating in the constructor.
		this.duck = duck;
	}
  
	

	public void quack() {
		//when quack() is called, we delegate the call to the Quackable we re decorating...
		duck.quack();
		//then we increase the number of quacks.
		numberOfQuacks++;
	}
 
	public static int getQuacks() {
		return numberOfQuacks;
	}
 
	public void registerObserver(Observer observer) {
		duck.registerObserver(observer);
	}
 
	public void notifyObservers() {
		duck.notifyObservers();
	}
   
	public String toString() {
		return duck.toString();
	}
}
