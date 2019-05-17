package designPatterns.compound.ex1;

public interface Observer {
	
	//The update(), is passed the QuackObservablethat is quacking.
	public void update(QuackObservable duck);
}
